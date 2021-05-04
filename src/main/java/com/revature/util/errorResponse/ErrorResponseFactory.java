package com.revature.util.errorResponse;

import com.revature.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorResponseFactory {

    public ErrorResponseDTO generateErrorResponse(final int status,final String message) {
        return new ErrorResponseDTO(status, message, System.currentTimeMillis());
    }

    public ErrorResponseDTO generateErrorResponse(final HttpStatus status) {
        return new ErrorResponseDTO(status.value(), status.toString(), System.currentTimeMillis());
    }

}
