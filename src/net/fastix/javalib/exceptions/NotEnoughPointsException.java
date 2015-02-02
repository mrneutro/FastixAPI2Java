package net.fastix.javalib.exceptions;

public class NotEnoughPointsException extends FileRequestException {
    public NotEnoughPointsException(String message) {
        super(message);
    }

    public NotEnoughPointsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPointsException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
