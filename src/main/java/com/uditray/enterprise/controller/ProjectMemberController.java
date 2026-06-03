package com.uditray.enterprise.controller;

import com.uditray.enterprise.entity.Project;
import com.uditray.enterprise.entity.User;
import com.uditray.enterprise.service.ProjectMemberService;
import com.uditray.enterprise.repository.ProjectRepository;
import com.uditray.enterprise.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService memberService;
    private final ProjectRepository projectRepository;

    // ADD MEMBER
    @PostMapping("/{projectId}/members/{userId}")
    public Project addMember(
            @PathVariable Long projectId,
            @PathVariable Long userId
    ) {
        return memberService.addMember(projectId, userId);
    }

    // REMOVE MEMBER
    @DeleteMapping("/{projectId}/members/{userId}")
    public Project removeMember(
            @PathVariable Long projectId,
            @PathVariable Long userId
    ) {
        return memberService.removeMember(projectId, userId);
    }


    @GetMapping("/{projectId}/members")
    public Set<User> getMembers(
            @PathVariable Long projectId
    ) {
        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Project not found")
                        );

        return project.getMembers();
    }
}