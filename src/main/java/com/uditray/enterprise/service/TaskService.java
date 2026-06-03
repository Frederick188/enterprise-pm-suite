package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.CreateTaskRequest;
import com.uditray.enterprise.dto.UpdateTaskStatusRequest;
import com.uditray.enterprise.entity.Project;
import com.uditray.enterprise.entity.Task;
import com.uditray.enterprise.entity.TaskStatus;
import com.uditray.enterprise.entity.User;
import com.uditray.enterprise.repository.ProjectRepository;
import com.uditray.enterprise.repository.TaskRepository;
import com.uditray.enterprise.repository.UserRepository;
import com.uditray.enterprise.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.uditray.enterprise.exception.ResourceNotFoundException;
import com.uditray.enterprise.exception.UnauthorizedException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private final NotificationService notificationService;
    private final AuditService auditService;

    // -------------------------
    // Get current logged-in user
    // -------------------------
    private User getCurrentUser() {
        return userRepository.findByEmail(
                SecurityUtils.getCurrentUserEmail()
        ).orElseThrow();
    }

    // -------------------------
    // PROJECT MEMBER CHECK (STEP B CORE)
    // -------------------------
    private void checkProjectAccess(Project project, User user) {

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

    // -------------------------
    // CREATE TASK
    // -------------------------
    public Task createTask(CreateTaskRequest request) {

        User currentUser = getCurrentUser();

        Project project =
                projectRepository.findByIdAndOrganization(
                        request.getProjectId(),
                        currentUser.getOrganization()
                ).orElseThrow(() ->
                        new ResourceNotFoundException("Project not found")
                );

        // 🔐 STEP B: creator must be project member
        checkProjectAccess(project, currentUser);

        User assignee =
                userRepository.findById(
                        request.getAssigneeId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        // 🔐 STEP B: assignee must also be project member
        checkProjectAccess(project, assignee);

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(TaskStatus.TODO)
                .project(project)
                .assignee(assignee)
                .build();

        Task savedTask = taskRepository.save(task);

        notificationService.sendNotification(
                "New task created: " + savedTask.getTitle()
        );

        auditService.log(
                currentUser.getEmail(),
                "CREATE",
                "TASK",
                savedTask.getId()
        );

        return savedTask;
    }

    // -------------------------
    // GET TASKS BY PROJECT
    // -------------------------
    public List<Task> getTasks(Long projectId) {

        User currentUser = getCurrentUser();

        Project project =
                projectRepository.findByIdAndOrganization(
                        projectId,
                        currentUser.getOrganization()
                ).orElseThrow(() ->
                        new ResourceNotFoundException("Project not found")
                );

        // 🔐 STEP B: only project members can view tasks
        checkProjectAccess(project, currentUser);

        return taskRepository.findByProject(project);
    }

    // -------------------------
    // UPDATE TASK STATUS
    // -------------------------
    public Task updateStatus(
            Long taskId,
            UpdateTaskStatusRequest request
    ) {

        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found")
                );

        Project project = task.getProject();

        // 🔐 ORGANIZATION CHECK (multi-tenant)
        if (!project.getOrganization().getId()
                .equals(currentUser.getOrganization().getId())) {
            throw new UnauthorizedException(
                    "You cannot access this task"
            );
        }

        // 🔐 STEP B: project membership check
        checkProjectAccess(project, currentUser);

        task.setStatus(request.getStatus());

        Task updatedTask = taskRepository.save(task);

        notificationService.sendNotification(
                "Task status updated to: " + updatedTask.getStatus()
        );

        auditService.log(
                currentUser.getEmail(),
                "STATUS_CHANGE",
                "TASK",
                updatedTask.getId()
        );

        return updatedTask;
    }
}