package net.fastix.javalib;


import net.fastix.javalib.common.ApiAction;
import net.fastix.javalib.common.ApiRequest;
import net.fastix.javalib.common.ApiResponse;
import net.fastix.javalib.exceptions.ApiRequestLimit;
import net.fastix.javalib.exceptions.FastixConnectionException;
import net.fastix.javalib.exceptions.FileRequestException;
import net.fastix.javalib.exceptions.RequestUnsuccessfulException;
import net.fastix.javalib.hold.*;
import net.fastix.javalib.hold.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FastixClient implements FastixAPI {
    protected String apiKey;
    protected String apiHost = "fastix.ru/api_v2/?";
    protected String apiVersion;
    protected String HTTPS_SCHEME = "https://";
    protected String APP_NAME = "Java Lib";

    private JSONParser jsonParser;

    public FastixClient() {
        initialize();
    }

    public FastixClient(String apiKey) {
        this(apiKey, "v2");
    }

    public FastixClient(String apiKey, String apiVersion) {
        this.apiKey = apiKey;
        this.apiVersion = apiVersion;
        initialize();
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey.getKey();
    }

    public Account getAccount() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Account) perform(new ApiRequest(ApiAction.ACCOUNT_INFO)).getData();
    }

    public Nodes getNodes() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Nodes) perform(new ApiRequest(ApiAction.NODES)).getData();
    }

    public Prices getPrices() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Prices) perform(new ApiRequest(ApiAction.PRICES)).getData();
    }

    public File getLinkInfo(String link) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"link=" + link};
        return (File) perform(new ApiRequest(ApiAction.CHECK, params)).getData();
    }

    public DirectedFile getDirectLink(String link) throws FastixConnectionException, RequestUnsuccessfulException {
        return getDirectLink(link, null, false);
    }

    public DirectedFile getDirectLink(String link, boolean forsedSSL) throws FastixConnectionException, RequestUnsuccessfulException {
        return getDirectLink(link, null, forsedSSL);
    }

    public DirectedFile getDirectLink(String link, String ip) throws FastixConnectionException, RequestUnsuccessfulException {
        return getDirectLink(link, ip, false);
    }

    public DirectedFile getDirectLink(String link, String ip, boolean forsedSSL) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"link=" + link, "ip=" + ip, "ssl=" + forsedSSL};
        return (DirectedFile) perform(new ApiRequest(ApiAction.DIRECT, params)).getData();
    }

    public HistoryFiles getHistoryFiles(int from, int to) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"from=" + from, "till=" + to};
        return (HistoryFiles) perform(new ApiRequest(ApiAction.DWNLOG, params)).getData();
    }

    public HistoryFile getHistoryFile(String fileID) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"id=" + fileID};
        return (HistoryFile) perform(new ApiRequest(ApiAction.HFILE, params)).getData();
    }

    public void setFileDisabled(String fileID) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"id=" + fileID};
        perform(new ApiRequest(ApiAction.REMOVEFILE, params));
    }

    public void refreshStatistic() throws FastixConnectionException, RequestUnsuccessfulException {
        perform(new ApiRequest(ApiAction.REFRESHSTAT));
    }

    public Status getServiceStatus() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Status) perform(new ApiRequest(ApiAction.STATUS)).getData();
    }

    public Shares getSupportedShares() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Shares) perform(new ApiRequest(ApiAction.FILESHARES)).getData();
    }

    public Sources getSupportedSources() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Sources) perform(new ApiRequest(ApiAction.SOURCES)).getData();
    }

    public Liches getSupportedLiches() throws FastixConnectionException, RequestUnsuccessfulException {
        return (Liches) perform(new ApiRequest(ApiAction.ANTILICHES)).getData();
    }

    public ApiKey getApiKey(String email, String password) throws FastixConnectionException, RequestUnsuccessfulException {
        String[] params = {"email=" + email, "password=" + password};
        return (ApiKey) perform(new ApiRequest(ApiAction.APIKEY, params)).getData();
    }

    private void initialize() {
        jsonParser = new JSONParser();
    }

    private ApiResponse perform(ApiRequest request) throws FastixConnectionException, RequestUnsuccessfulException {
        URL url = createUrl(request);
        String response = doPost(url, createRequestData(request));
        ApiResponse apiResponse = new ApiResponse(request.getApiAction(), true);
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            if (jsonObject.containsKey("error") && ((boolean) jsonObject.get("error")) == true) {
                parseError(jsonObject);
            }
            if (request.getApiAction().getSub() == "getdwnlinkbyid") { //HotFix of error in Fastix API interface
                jsonObject = (JSONObject) jsonObject.get("data");
            }
            Constructor<?> ctor = request.getApiAction().getClazz().getConstructor(JSONObject.class);
            apiResponse.setData(ctor.newInstance(new Object[]{jsonObject}));

        } catch (org.json.simple.parser.ParseException ex) {
            throw new RequestUnsuccessfulException(ex.getMessage(), ex);
        } catch (ReflectiveOperationException ex) {
            throw new RequestUnsuccessfulException(ex.getMessage(), ex);
        }
        return apiResponse;
    }

    private void parseError(JSONObject jsonObject) throws RequestUnsuccessfulException {
        int errorCode = ((Number) jsonObject.get("error_code")).intValue();
        String errorMessage = (String) jsonObject.get("error_txt");
        if (errorCode >= 500) {
            throw new ApiRequestLimit(errorMessage, errorCode);
        } else if (errorCode >= 100) {
            throw new FileRequestException(errorMessage, errorCode);
        } else {
            throw new RequestUnsuccessfulException("not recognised error");
        }
    }

    private String createRequestData(ApiRequest request) {
        StringBuilder data = new StringBuilder("");
        if (request.isApiCheck()) data.append("apikey=" + apiKey + "&");

        data.append("sub=" + request.getApiAction().getSub() + "&");
        if (null != request.getParams()) {
            for (String param : request.getParams())
                data.append(param + "&");
        }
        return data.toString();
    }

    private URL createUrl(ApiRequest request) throws FastixConnectionException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(HTTPS_SCHEME);
            sb.append(apiHost);
            sb.append("method=post&");
            return new URL(sb.toString());
        } catch (MalformedURLException ex) {
            throw new FastixConnectionException(ex.getMessage(), ex);
        }
    }

    private String doPost(URL url, String urlParameters) throws FastixConnectionException {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestProperty("User-Agent", APP_NAME);

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException ex) {
            throw new FastixConnectionException(ex.getMessage(), ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
