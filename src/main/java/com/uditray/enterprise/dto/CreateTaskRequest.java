package com.uditray.enterprise.dto;

import com.uditray.enterprise.entity.TaskPriority;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotNull(message = "Assignee is required")
    private Long assigneeId;

    @NotNull(message = "Project is required")
    private Long projectId;
}