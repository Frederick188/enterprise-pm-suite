package com.uditray.enterprise.controller;

import com.uditray.enterprise.entity.AuditLog;
import com.uditray.enterprise.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getLogs() {
        return auditLogRepository.findAll();
    }
}