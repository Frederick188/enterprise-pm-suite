package com.uditray.enterprise.service;

import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.repository.*;
import com.uditray.enterprise.exception.ResourceNotFoundException;
import com.uditray.enterprise.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // -------------------------
    // ADD MEMBER TO PROJECT
    // -------------------------
    public Project addMember(Long projectId, Long userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found")
                );

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        if (!project.getMembers().contains(user)) {
            project.getMembers().add(user);
        }

        return projectRepository.save(project);
    }

    // -------------------------
    // REMOVE MEMBER FROM PROJECT
    // -------------------------
    public Project removeMember(Long projectId, Long userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found")
                );

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        project.getMembers().remove(user);

        return projectRepository.save(project);
    }

    // -------------------------
    // 🔐 CHECK ACCESS (IMPORTANT FOR SECURITY)
    // -------------------------
    public void checkAccess(Project project, User user) {

        boolean isMember = project.getMembers()
                .stream()
                .anyMatch(member ->
                        member.getId().equals(user.getId())
                );

        if (!isMember) {
            throw new UnauthorizedException(
                    "You are not a member of this project"
            );
        }
    }
}