package com.uditray.enterprise.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateSprintRequest {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;
}