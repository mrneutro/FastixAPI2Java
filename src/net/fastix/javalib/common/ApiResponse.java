package net.fastix.javalib.common;

/**
 * API respinse holder
 */
public class ApiResponse {
    private ApiAction apiAction;
    private Object data;
    private boolean requestSuccess;

    public ApiResponse(ApiAction apiAction, boolean requestSuccess) {
        this(apiAction, null, requestSuccess);
    }

    public ApiResponse(ApiAction apiAction, Object data, boolean requestSuccess) {
        this.apiAction = apiAction;
        this.data = data;
        this.requestSuccess = requestSuccess;
    }

    public ApiAction getApiAction() {
        return apiAction;
    }

    public Object getData() {
        return data;
    }

    public boolean isRequestSuccess() {
        return requestSuccess;
    }

    public void setApiAction(ApiAction apiAction) {
        this.apiAction = apiAction;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        this.requestSuccess = requestSuccess;
    }
}
