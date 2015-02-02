package net.fastix.javalib.exceptions;

public class FileRequestException extends RequestUnsuccessfulException {
    public FileRequestException(String message) {
        super(message);
    }

    public FileRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileRequestException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
