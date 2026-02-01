package com.softwareplus.erp_finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralLedgerDTO {
    private Long id;
    private LocalDate date;
    private String reference;
    private String description;
    private String accountCode;
    private Double debit;   // Change to BigDecimal if your Model uses it
    private Double credit;  // Change to BigDecimal if your Model uses it
}