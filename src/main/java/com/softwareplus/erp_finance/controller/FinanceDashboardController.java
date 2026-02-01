package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.dto.DashboardSummaryDTO;
import com.softwareplus.erp_finance.service.FinanceDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/finance/dashboard")
// Allow your React dev server (3000) to access this data
@CrossOrigin(origins = "http://localhost:3000") 
public class FinanceDashboardController {

    private static final Logger logger = LoggerFactory.getLogger(FinanceDashboardController.class);

    @Autowired
    private FinanceDashboardService dashboardService;

    /**
     * Endpoint to fetch all critical financial KPIs for the main Dashboard.
     * URL: GET http://localhost:8080/api/finance/dashboard/summary
     */
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        try {
            logger.info("Fetching real-time dashboard financial summaries...");
            DashboardSummaryDTO summary = dashboardService.getDashboardSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error generating dashboard summary: ", e);
            // Return a structured error so the frontend can display a warning
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error retrieving financial metrics. Please check database connectivity.");
        }
    }
}