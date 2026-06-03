package com.uditray.enterprise.service;

import com.uditray.enterprise.entity.Project;
import com.uditray.enterprise.entity.User;
import com.uditray.enterprise.exception.ResourceNotFoundException;
import com.uditray.enterprise.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectSecurityService {

    private final ProjectRepository projectRepository;

    // Get logged-in user email from JWT
    public String getCurrentUserEmail() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    // Check if user is member of project
    public void checkProjectAccess(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found")
                );

        String email = getCurrentUserEmail();

        boolean isMember = project.getMembers()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));

        if (!isMember) {
            throw new RuntimeException("ACCESS DENIED: Not a project member");
        }
    }
}