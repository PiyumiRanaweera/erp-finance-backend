package com.erp.finance.model; // Adjust based on your package name

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "accounts_payable") // Maps to erp_finance database table
@Data // Automatically generates Getters, Setters, and toString
public class AccountsPayable {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String vendor;
    
    @Column(name = "invoice_no", unique = true)
    private String invoiceNo;
    
    private LocalDate date;
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    private Double amount;
    
    private String status; // "Overdue", "Paid", "Pending"
}