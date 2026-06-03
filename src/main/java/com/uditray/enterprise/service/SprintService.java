package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.CreateSprintRequest;
import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.repository.*;
import com.uditray.enterprise.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.uditray.enterprise.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Sprint createSprint(
            CreateSprintRequest request
    ) {
        User currentUser =
                userRepository.findByEmail(
                        SecurityUtils
                                .getCurrentUserEmail()
                ).orElseThrow();

        Project project =
                projectRepository.findByIdAndOrganization(
                        request.getProjectId(),
                        currentUser.getOrganization()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"
                        )
                );

        Sprint sprint = Sprint.builder()
                .name(request.getName())
                .startDate(
                        request.getStartDate()
                )
                .endDate(
                        request.getEndDate()
                )
                .status("PLANNED")
                .project(project)
                .build();

        return sprintRepository.save(sprint);
    }

    // NEW METHOD
    public Sprint getSprint(
            Long sprintId
    ) {

        return sprintRepository
                .findById(sprintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sprint not found"
                        )
                );
    }

    public Sprint updateStatus(
            Long sprintId,
            String status
    ) {

        Sprint sprint =
                sprintRepository.findById(sprintId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sprint not found"
                                )
                        );

        sprint.setStatus(status);

        return sprintRepository.save(sprint);
    }
}