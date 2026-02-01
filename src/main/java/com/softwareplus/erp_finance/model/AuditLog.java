package com.softwareplus.erp_finance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String action; // e.g., "JOURNAL_POSTED", "BANK_ADJUSTMENT"
    private String performedBy; // e.g., "Admin User"
    private String timestamp;
    private String details; // e.g., "Posted JNL-001 for $5,000"
    private String ipAddress;
}