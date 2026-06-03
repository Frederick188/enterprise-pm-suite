package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.*;
import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.repository.*;
import com.uditray.enterprise.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.uditray.enterprise.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    private User getCurrentUser() {
        String email =
                SecurityUtils.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow();
    }

    public Project createProject(
            CreateProjectRequest request
    ) {
        User currentUser = getCurrentUser();

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .organization(
                        currentUser.getOrganization()
                )
                .build();

        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        User currentUser = getCurrentUser();

        return projectRepository.findByOrganization(
                currentUser.getOrganization()
        );
    }

    public Project updateProject(
            Long id,
            UpdateProjectRequest request
    ) {
        User currentUser = getCurrentUser();

        Project project =
                projectRepository.findByIdAndOrganization(
                        id,
                        currentUser.getOrganization()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"
                        )
                );

        project.setName(request.getName());
        project.setDescription(
                request.getDescription()
        );

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        User currentUser = getCurrentUser();

        Project project =
                projectRepository.findByIdAndOrganization(
                        id,
                        currentUser.getOrganization()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"
                        )
                );

        projectRepository.delete(project);
    }
}