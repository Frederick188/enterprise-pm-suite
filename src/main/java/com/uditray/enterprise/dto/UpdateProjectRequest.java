package com.uditray.enterprise.dto;

import lombok.Data;

@Data
public class UpdateProjectRequest {
    private String name;
    private String description;
}