package com.uditray.enterprise.service;

import com.uditray.enterprise.dto.CreateCommentRequest;
import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.exception.ResourceNotFoundException;
import com.uditray.enterprise.repository.CommentRepository;
import com.uditray.enterprise.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final AuthService authService;
    private final ProjectMemberService projectMemberService;

    // -------------------------
    // CURRENT USER (CLEAN WAY)
    // -------------------------
    private User getCurrentUser() {
        return authService.getCurrentUser();
    }

    // -------------------------
    // CREATE COMMENT
    // -------------------------
    public Comment addComment(Long taskId, CreateCommentRequest request) {

        User user = getCurrentUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found")
                );

        // 🔐 PROPER SECURITY CHECK (NO HACKY getTasks CALL)
        projectMemberService.checkAccess(
                task.getProject(),
                user
        );

        Comment comment = Comment.builder()
                .message(request.getMessage())
                .createdBy(user.getEmail())
                .createdAt(LocalDateTime.now())
                .task(task)
                .build();

        return commentRepository.save(comment);
    }

    // -------------------------
    // GET COMMENTS
    // -------------------------
    public List<Comment> getComments(Long taskId) {

        User user = getCurrentUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found")
                );

        // 🔐 SECURITY CHECK
        projectMemberService.checkAccess(
                task.getProject(),
                user
        );

        return commentRepository.findByTask(task);
    }
}