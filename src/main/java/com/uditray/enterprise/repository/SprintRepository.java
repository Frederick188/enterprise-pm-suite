package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository
        extends JpaRepository<Sprint, Long> {
}