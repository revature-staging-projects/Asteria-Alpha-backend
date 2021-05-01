package com.revature.Exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException() {
        super("no user found");
    }
    public NoUserFoundException(final String message) {
        super(message);
    }
}
