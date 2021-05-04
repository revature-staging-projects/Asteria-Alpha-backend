package com.revature.aspects.errorResponse;

import com.revature.Exceptions.*;
import com.revature.dto.ErrorResponseDTO;
import com.revature.util.errorResponse.ErrorResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class ErrorResponseAspect {

    private final ErrorResponseFactory response_factory;

    public ErrorResponseAspect(final ErrorResponseFactory response_factory) {
        this.response_factory = response_factory;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleExistingUser(final AlreadyExistingUserException e) {
        return response_factory.generateErrorResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleInvalidRequest(final InvalidRequestException e) {
        return response_factory.generateErrorResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNoSuchUser(final NoSuchUserException e) {
        return response_factory.generateErrorResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNoUserFound(final NoUserFoundException e) {
        return response_factory.generateErrorResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleResourceNotFoound(final ResourceNotFoundException e) {
        return response_factory.generateErrorResponse(HttpStatus.NOT_FOUND);
    }

}
