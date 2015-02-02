package net.fastix.javalib.common;


import net.fastix.javalib.hold.*;

/**
 * Api actions container
 */
public enum ApiAction {
    ACCOUNT_INFO("getaccountdetails", Account.class, true),
    PRICES("getprices", Prices.class, true),
    NODES("proxyservers", Nodes.class, true),
    CHECK("checkfile", File.class, true),
    DIRECT("getdirectlink", DirectedFile.class, true),
    DWNLOG("getdwnlog", HistoryFiles.class, true),
    HFILE("getdwnlinkbyid", HistoryFile.class, true),
    REMOVEFILE("deletedwnlink", GenericResponse.class, true),
    REFRESHSTAT("refresh_balance", GenericResponse.class, true),
    STATUS("directing_status", Status.class, true),
    FILESHARES("allowed_fileshares", Shares.class, true),
    SOURCES("allowed_sources", Sources.class, true),
    ANTILICHES("allowed_antiliches", Liches.class, true),
    APIKEY("get_apikey", ApiKey.class, false);

    private boolean apiCheck;
    private String sub;
    private Class clazz;

    /**
     * Method creates ApiAction object
     *
     * @param sub      fastix ?sub= action
     * @param clazz    return class container
     * @param apiCheck needs API key?
     */
    ApiAction(String sub, Class clazz, boolean apiCheck) {
        this.sub = sub;
        this.apiCheck = apiCheck;
        this.clazz = clazz;
    }

    /**
     * @return Returns true if api request requires apiKey
     */
    public boolean isApiCheck() {
        return apiCheck;
    }

    /**
     * @return Fastix sub
     */
    public String getSub() {
        return sub;
    }

    /**
     * @return class
     */
    public Class getClazz() {
        return clazz;
    }
}
