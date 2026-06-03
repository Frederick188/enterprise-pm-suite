package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.Organization;
import com.uditray.enterprise.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository
        extends JpaRepository<Project, Long> {

    List<Project> findByOrganization(
            Organization organization
    );

    Optional<Project> findByIdAndOrganization(
            Long id,
            Organization organization
    );
}