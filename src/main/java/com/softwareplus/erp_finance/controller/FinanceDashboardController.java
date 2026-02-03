package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.dto.DashboardSummaryDTO;
import com.softwareplus.erp_finance.service.FinanceDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/finance/dashboard")
// Note: We removed the hardcoded @CrossOrigin from here 
// so that WebConfig.java can handle both Localhost and Vercel URLs.
public class FinanceDashboardController {

    private static final Logger logger = LoggerFactory.getLogger(FinanceDashboardController.class);

    @Autowired
    private FinanceDashboardService dashboardService;

    /**
     * Endpoint to fetch all critical financial KPIs for the main Dashboard.
     * Production URL: https://your-backend-url.com/api/finance/dashboard/summary
     */
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        try {
            logger.info("API REQUEST: Fetching real-time dashboard financial summaries...");
            DashboardSummaryDTO summary = dashboardService.getDashboardSummary();
            
            if (summary == null) {
                logger.warn("Database returned empty summary.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data available.");
            }
            
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error generating dashboard summary: ", e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error retrieving financial metrics. Please check database connectivity.");
        }
    }
}