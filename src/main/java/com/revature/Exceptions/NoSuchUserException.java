package com.revature.Exceptions;

/**
 * Exception to be thrown when user cannot be found or does not exists.
 */
public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(final String message) {
        super(message);
    }

    public NoSuchUserException() {
        super("No such users.");
    }
}
