package net.fastix.javalib.common;

/**
 * ApiRequest holder
 */

public class ApiRequest {
    private ApiAction apiAction;

    private String[] params;

    public ApiRequest(ApiAction apiAction) {
        this(apiAction, null);
    }

    public ApiRequest(ApiAction apiAction, String[] params) {
        this.apiAction = apiAction;
        this.params = params;
    }

    /**
     * @return Returns true if api request requires apiKey
     */
    public boolean isApiCheck() {
        return apiAction.isApiCheck();
    }

    /**
     * @return {@link ApiAction}
     */
    public ApiAction getApiAction() {
        return apiAction;
    }

    /**
     * @return request params
     */
    public String[] getParams() {
        return params;
    }

}
