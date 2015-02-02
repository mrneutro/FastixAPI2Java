package net.fastix.javalib.exceptions;

public class ApiRequestLimit extends RequestUnsuccessfulException {
    public ApiRequestLimit(String message) {
        super(message);
    }

    public ApiRequestLimit(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestLimit(String message, int errorCode) {
        super(message, errorCode);
    }
}
