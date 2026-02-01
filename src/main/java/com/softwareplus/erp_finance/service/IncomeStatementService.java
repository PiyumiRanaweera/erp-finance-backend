package com.softwareplus.erp_finance.service;

import com.softwareplus.erp_finance.dto.IncomeStatementDTO;
import com.softwareplus.erp_finance.repository.JournalLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncomeStatementService {

    @Autowired
    private JournalLineRepository journalLineRepository;

    public IncomeStatementDTO getIncomeStatement(LocalDate startDate, LocalDate endDate) {
        // 1. Fetch Aggregated Totals using the Date Parameters
        // These now call the updated repository methods with (:start, :end)
        Double revenue = journalLineRepository.calculateTotalRevenue(startDate, endDate);
        Double expenses = journalLineRepository.calculateTotalExpenses(startDate, endDate);
        
        // 2. Calculate Net Profit (with null safety)
        Double safeRevenue = (revenue != null) ? revenue : 0.0;
        Double safeExpenses = (expenses != null) ? expenses : 0.0;
        Double netProfit = safeRevenue - safeExpenses;

        // 3. Fetch Detailed Breakdown for the "Drill-down" view using Date Parameters
        List<Object[]> details = journalLineRepository.getIncomeStatementDetails(startDate, endDate);
        Map<String, Double> breakdown = new HashMap<>();

        if (details != null) {
            for (Object[] row : details) {
                String code = (String) row[0];
                Double debits = (row[1] != null) ? (Double) row[1] : 0.0;
                Double credits = (row[2] != null) ? (Double) row[2] : 0.0;

                // Accounting Logic: 
                // Revenue (4xxx) increases with Credit: (Credits - Debits)
                // Expenses (5xxx) increases with Debit: (Debits - Credits)
                Double balance = code.startsWith("4") ? (credits - debits) : (debits - credits);
                breakdown.put(code, balance);
            }
        }

        return new IncomeStatementDTO(safeRevenue, safeExpenses, netProfit, breakdown);
    }
}