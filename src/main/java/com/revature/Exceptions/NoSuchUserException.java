package com.revature.Exceptions;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(final String message) {
        super(message);
    }

    public NoSuchUserException() {
        super();
    }
}
