package com.revature.Exceptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("sorry, but that user does not exists");
    }
    public UserNotExistsException(final String message) {
        super(message);
    }
}
