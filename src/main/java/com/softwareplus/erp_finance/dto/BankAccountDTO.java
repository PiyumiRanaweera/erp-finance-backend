package com.softwareplus.erp_finance.dto;

import lombok.Data;              // Add this
import lombok.AllArgsConstructor; // Add this
import lombok.NoArgsConstructor;

// BankAccountDTO.java
@Data
@AllArgsConstructor
public class BankAccountDTO {
    private String accountName;
    private String bankName;
    private String accountNumber;
    private Double currentBalance;
    private String status; // Reconciled, Pending
}