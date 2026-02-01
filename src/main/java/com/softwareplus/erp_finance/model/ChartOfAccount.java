package com.softwareplus.erp_finance.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "chart_of_accounts")
@Data
public class ChartOfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountCode;

    private String accountName;

    @Column(nullable = false)
    private String accountType; // Asset, Liability, Equity, Revenue, Expense

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ChartOfAccount parent; // Supports Hierarchical management
}