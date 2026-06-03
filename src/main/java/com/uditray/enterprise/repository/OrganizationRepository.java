package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository
        extends JpaRepository<Organization, Long> {
}

