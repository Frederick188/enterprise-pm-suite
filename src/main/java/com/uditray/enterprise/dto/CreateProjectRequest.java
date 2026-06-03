package com.uditray.enterprise.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CreateProjectRequest {

    @NotBlank(message = "Project name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}

