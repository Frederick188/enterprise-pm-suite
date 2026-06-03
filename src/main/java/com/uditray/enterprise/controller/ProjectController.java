package com.uditray.enterprise.controller;

import com.uditray.enterprise.dto.*;
import com.uditray.enterprise.entity.Project;
import com.uditray.enterprise.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Project create(
            @Valid @RequestBody CreateProjectRequest request
    ) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<Project> getAll() {
        return projectService.getProjects();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Project update(
            @PathVariable Long id,
            @RequestBody UpdateProjectRequest request
    ) {
        return projectService.updateProject(
                id,
                request
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(
            @PathVariable Long id
    ) {
        projectService.deleteProject(id);
    }
}