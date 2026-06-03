package com.uditray.enterprise.service;

import com.uditray.enterprise.entity.AuditLog;
import com.uditray.enterprise.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void log(
            String userEmail,
            String action,
            String entityType,
            Long entityId
    ) {

        AuditLog auditLog =
                AuditLog.builder()
                        .userEmail(userEmail)
                        .action(action)
                        .entityType(entityType)
                        .entityId(entityId)
                        .timestamp(LocalDateTime.now())
                        .build();

        auditLogRepository.save(auditLog);
    }
}