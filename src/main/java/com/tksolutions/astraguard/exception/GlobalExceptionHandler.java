package com.tksolutions.astraguard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RiskServiceUnavailableException.class)
    public ResponseEntity<ApiErrorResponse> handleRiskServiceDown(
            RiskServiceUnavailableException ex) {

        ApiErrorResponse error = new ApiErrorResponse(
                ex.getMessage(),
                "RISK_SERVICE_UNAVAILABLE"
        );

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidTransaction(
            InvalidTransactionException ex) {

        ApiErrorResponse error = new ApiErrorResponse(
                ex.getMessage(),
                "INVALID_TRANSACTION"
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception ex) {

        ApiErrorResponse error = new ApiErrorResponse(
                "Internal server error",
                "INTERNAL_ERROR"
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
