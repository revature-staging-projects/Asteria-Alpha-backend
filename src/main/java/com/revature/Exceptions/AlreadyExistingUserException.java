package com.revature.Exceptions;

public class AlreadyExistingUserException extends RuntimeException{
    public AlreadyExistingUserException() {
        super("User already exists");
    }

    public AlreadyExistingUserException(final String message) {
        super(message);
    }

}
