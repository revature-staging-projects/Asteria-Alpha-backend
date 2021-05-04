package com.revature.dto;

public class ErrorResponseDTO {

    private final int status;
    private final String message;
    private final Long time;

    public ErrorResponseDTO(final int status, final String message, final Long time) {
        this.status  = status;
        this.message = message;
        this.time    = time;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getTime() {
        return time;
    }
}
