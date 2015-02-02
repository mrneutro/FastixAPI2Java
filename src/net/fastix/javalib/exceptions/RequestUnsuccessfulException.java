package net.fastix.javalib.exceptions;

public class RequestUnsuccessfulException extends Exception {
    public RequestUnsuccessfulException(String message) {
        super(message);
    }

    public RequestUnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestUnsuccessfulException(String message, int errorCode) {
        super(message + " Error code: " + errorCode);
    }
}
