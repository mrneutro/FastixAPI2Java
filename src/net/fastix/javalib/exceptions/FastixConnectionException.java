package net.fastix.javalib.exceptions;

public class FastixConnectionException extends Exception {
    public FastixConnectionException(String message) {
        super(message);
    }

    public FastixConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
