package com.uditray.enterprise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileAttachment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String fileName;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;
}