package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated file holder
 */
public class DirectedFile extends AbstractFile {
    private String id;
    private String ipRestriction;
    private boolean forsedSSL;
    private String downloadLink;
    private List<String> mirrors;

    public DirectedFile(JSONObject json) {
        super(json);
        id = (String) json.get("id");
        ipRestriction = !json.containsKey("restriction") ? null : (json.get("restriction") instanceof String ? ((String) json.get("restriction")) : null);
        forsedSSL = json.containsKey("ssl") && (json.get("ssl") != null && (boolean) json.get("ssl"));
        downloadLink = !json.containsKey("ssl") ? null : (String) json.get("downloadlink");
        if (json.containsKey("mirrors") && json.get("mirrors") != null) {
            JSONArray jMirrors = (JSONArray) json.get("mirrors");
            if (jMirrors.size() > 0) {
                mirrors = new ArrayList<>();
                for (Object o : jMirrors) mirrors.add((String) o);
            }
        }
    }

    /**
     * @return file ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return Restricted to IP
     */
    public String getIpRestriction() {
        return ipRestriction;
    }

    /**
     * @return true if downloading is only via SSL
     */
    public boolean isForsedSSL() {
        return forsedSSL;
    }

    /**
     * @return mainFastix download link
     */
    public String getDownloadLink() {
        return downloadLink;
    }

    /**
     * @return mirrors
     */
    public List<String> getMirrors() {
        return mirrors;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "id='" + id + '\'' +
                ", ipRestriction='" + ipRestriction + '\'' +
                ", forsedSSL=" + forsedSSL +
                ", downloadLink='" + downloadLink + '\'' +
                ", mirrors=" + mirrors +
                '}';
    }
}
