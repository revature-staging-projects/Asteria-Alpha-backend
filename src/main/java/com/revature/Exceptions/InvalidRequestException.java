package com.revature.Exceptions;

/**
 * Exception to be thrown when user makes an invalid request of the server.
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("invalid request");
    }

    public InvalidRequestException(final String message) {
        super(message);
    }
}
