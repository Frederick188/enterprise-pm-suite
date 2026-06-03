package com.uditray.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String createdBy;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}