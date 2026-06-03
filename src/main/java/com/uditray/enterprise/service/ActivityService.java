package com.uditray.enterprise.service;

import com.uditray.enterprise.entity.*;
import com.uditray.enterprise.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityLogRepository activityRepository;

    // SAVE ACTIVITY
    public void logActivity(
            String action,
            String entityType,
            Long entityId,
            String message,
            String performedBy,
            Project project
    ) {
        ActivityLog log = ActivityLog.builder()
                .action(action)
                .entityType(entityType)
                .entityId(entityId)
                .message(message)
                .performedBy(performedBy)
                .timestamp(LocalDateTime.now())
                .project(project)
                .build();

        activityRepository.save(log);
    }

    // GET PROJECT ACTIVITY FEED
    public List<ActivityLog> getProjectActivity(Project project) {
        return activityRepository.findByProjectOrderByTimestampDesc(project);
    }
}