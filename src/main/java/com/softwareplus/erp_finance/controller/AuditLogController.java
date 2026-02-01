package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.model.AuditLog;
import com.softwareplus.erp_finance.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@CrossOrigin(origins = "http://localhost:3000")
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        // This allows Postman and React to see the data
        return auditLogRepository.findAll();
    }
}