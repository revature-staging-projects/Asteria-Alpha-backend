package com.revature.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("resource was not found.");
    }
}
