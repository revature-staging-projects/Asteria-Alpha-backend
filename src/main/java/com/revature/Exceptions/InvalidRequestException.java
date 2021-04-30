package com.revature.Exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("invalid request");
    }

    public InvalidRequestException(final String message) {
        super(message);
    }
}
