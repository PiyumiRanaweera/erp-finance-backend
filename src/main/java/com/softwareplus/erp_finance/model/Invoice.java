package com.softwareplus.erp_finance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vendor;
    private String invoiceNo;
    private LocalDate date;
    private LocalDate dueDate;
    private double amount;
    private String status; // e.g., "Overdue", "Pending", "Paid"

    public Invoice() {}
    // Getters and Setters...
}