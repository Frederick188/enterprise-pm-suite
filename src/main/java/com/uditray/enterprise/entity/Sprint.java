package com.uditray.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sprint {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    @ManyToOne
    private Project project;
}