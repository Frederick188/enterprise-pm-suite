package com.uditray.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;   // CREATE, UPDATE, COMMENT, UPLOAD

    private String entityType; // TASK, PROJECT, FILE, COMMENT

    private Long entityId;

    private String message;

    private String performedBy;

    private LocalDateTime timestamp;

    @ManyToOne
    private Project project;
}