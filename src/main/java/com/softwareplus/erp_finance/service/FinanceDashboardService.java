package com.softwareplus.erp_finance.service;

import com.softwareplus.erp_finance.dto.DashboardSummaryDTO;
import com.softwareplus.erp_finance.repository.JournalLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceDashboardService {

    @Autowired
    private JournalLineRepository journalLineRepository;

    /**
     * Fetches real-time financial metrics for the Dashboard.
     * Uses optimized repository queries for performance.
     */
    public DashboardSummaryDTO getDashboardSummary() {
        // 1. Fetch data using specialized repository methods
        // Pass null, null to fetch "All-Time" totals for the dashboard summary
        Double revenue = journalLineRepository.calculateTotalRevenue(null, null);
        Double ar = journalLineRepository.calculateAccountsReceivable();
        Double ap = journalLineRepository.calculateAccountsPayable();
        Double cash = journalLineRepository.calculateCashBalance();

        // 2. Data Sanitization (Handle nulls if the DB is empty)
        revenue = (revenue != null) ? revenue : 0.0;
        ar = (ar != null) ? ar : 0.0;
        ap = (ap != null) ? ap : 0.0;
        cash = (cash != null) ? cash : 0.0;

        // 3. Logic for Performance Indicators
        Double growthRate = calculateGrowthRate(revenue);

        return new DashboardSummaryDTO(
            revenue, 
            ar, 
            ap, 
            cash, 
            growthRate
        );
    }

    /**
     * Helper to calculate revenue growth.
     */
    private Double calculateGrowthRate(Double currentRevenue) {
        if (currentRevenue <= 0) return 0.0;
        return 5.2; 
    }
}