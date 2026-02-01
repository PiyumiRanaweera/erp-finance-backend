package com.softwareplus.erp_finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialBalanceDTO {
    private String accountCode;
    private String accountName;
    private Double debitBalance;
    private Double creditBalance;
    private Double netBalance;
}