package com.uditray.enterprise.exception;

import com.uditray.enterprise.dto.ErrorResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        ).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        404,
                        "NOT_FOUND",
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(
            UnauthorizedException.class
    )
    public ResponseEntity<ErrorResponse>
    handleUnauthorized(
            UnauthorizedException ex
    ) {

        return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        403,
                        "FORBIDDEN",
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneral(
            Exception ex
    ) {

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        500,
                        "INTERNAL_ERROR",
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ErrorResponse>
    handleValidation(
            MethodArgumentNotValidException ex
    ) {

        String errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                error.getField()
                                        + ": "
                                        + error.getDefaultMessage()
                        )
                        .collect(
                                Collectors.joining(", ")
                        );

        return ResponseEntity.badRequest()
                .body(
                        new ErrorResponse(
                                LocalDateTime.now(),
                                400,
                                "VALIDATION_ERROR",
                                errors
                        )
                );
    }
}