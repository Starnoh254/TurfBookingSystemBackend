package com.starnoh.turf.bookingsystem.backend.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleException(
            RuntimeException ex,
            HttpServletRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                "about:blank",
                "Internal Server Error",
                500,
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(500)
                .body(ApiResponse.error(error));
    }
}
