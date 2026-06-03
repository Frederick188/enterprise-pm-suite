package com.uditray.enterprise.controller;

import com.uditray.enterprise.dto.CreateCommentRequest;
import com.uditray.enterprise.entity.Comment;
import com.uditray.enterprise.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // ADD COMMENT
    @PostMapping
    public Comment addComment(
            @PathVariable Long taskId,
            @RequestBody CreateCommentRequest request
    ) {
        return commentService.addComment(taskId, request);
    }

    // GET COMMENTS
    @GetMapping
    public List<Comment> getComments(
            @PathVariable Long taskId
    ) {
        return commentService.getComments(taskId);
    }
}