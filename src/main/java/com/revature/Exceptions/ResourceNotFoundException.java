package com.revature.Exceptions;

/**
 * Exception to be thrown when database cannot find a resource which is being asked for.
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("resource was not found.");
    }
}
