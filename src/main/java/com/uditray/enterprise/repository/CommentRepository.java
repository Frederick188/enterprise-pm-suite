package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.Comment;
import com.uditray.enterprise.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTask(Task task);
}