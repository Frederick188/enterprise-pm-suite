package com.uditray.enterprise.controller;

import com.uditray.enterprise.dto.CreateSprintRequest;
import com.uditray.enterprise.entity.Sprint;
import com.uditray.enterprise.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @PostMapping
    public Sprint create(
            @RequestBody CreateSprintRequest request
    ) {
        return sprintService.createSprint(
                request
        );
    }

    @GetMapping("/{id}")
    public Sprint getSprint(
            @PathVariable Long id
    ) {
        return sprintService.getSprint(id);
    }


    @PatchMapping("/{id}/start")
    public Sprint start(
            @PathVariable Long id
    ) {
        return sprintService.updateStatus(
                id,
                "ACTIVE"
        );
    }

    @PatchMapping("/{id}/close")
    public Sprint close(
            @PathVariable Long id
    ) {
        return sprintService.updateStatus(
                id,
                "COMPLETED"
        );
    }
}