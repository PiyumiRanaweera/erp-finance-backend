package com.softwareplus.erp_finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor // <-- This is required for your Service to work
public class IncomeStatementDTO {
    private Double totalRevenue;
    private Double totalExpenses;
    private Double netIncome;
    private Map<String, Double> expenseBreakdown;
}