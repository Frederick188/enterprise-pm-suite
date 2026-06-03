package com.uditray.enterprise.dto;

import com.uditray.enterprise.entity.TaskStatus;
import lombok.Data;

@Data
public class UpdateTaskStatusRequest {
    private TaskStatus status;
}