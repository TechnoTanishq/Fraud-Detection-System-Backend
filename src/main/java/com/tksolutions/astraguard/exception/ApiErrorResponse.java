package com.tksolutions.astraguard.exception;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private String message;
    private String errorCode;
    private LocalDateTime timestamp;

    public ApiErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
