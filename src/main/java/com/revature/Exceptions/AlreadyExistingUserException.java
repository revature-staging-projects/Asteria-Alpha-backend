package com.revature.Exceptions;

/**
 * Exception thrown when a user attempts to register an already existing account.
 */
public class AlreadyExistingUserException extends RuntimeException{
    public AlreadyExistingUserException() {
        super("User already exists");
    }

    public AlreadyExistingUserException(final String message) {
        super(message);
    }

}
