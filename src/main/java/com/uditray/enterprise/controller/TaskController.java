package com.uditray.enterprise.controller;

import com.uditray.enterprise.dto.*;
import com.uditray.enterprise.entity.Task;
import com.uditray.enterprise.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task create(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        return taskService.createTask(request);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasks(
            @PathVariable Long projectId
    ) {
        return taskService.getTasks(projectId);
    }

    @PatchMapping("/{taskId}/status")
    public Task updateStatus(
            @PathVariable Long taskId,
            @RequestBody UpdateTaskStatusRequest request
    ) {
        return taskService.updateStatus(
                taskId,
                request
        );
    }
}