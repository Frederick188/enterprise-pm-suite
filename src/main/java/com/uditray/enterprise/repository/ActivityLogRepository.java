package com.uditray.enterprise.repository;

import com.uditray.enterprise.entity.ActivityLog;
import com.uditray.enterprise.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByProjectOrderByTimestampDesc(Project project);
}