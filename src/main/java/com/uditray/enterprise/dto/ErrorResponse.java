package com.uditray.enterprise.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;
}