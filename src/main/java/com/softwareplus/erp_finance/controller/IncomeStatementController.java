package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.dto.IncomeStatementDTO;
import com.softwareplus.erp_finance.service.IncomeStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/finance/reports")
// Allows your React Frontend (Port 3000) to communicate with this Backend
@CrossOrigin(origins = "http://localhost:3000") 
public class IncomeStatementController {

    @Autowired
    private IncomeStatementService incomeStatementService;

    /**
     * Generates a real-time Income Statement (P&L).
     * Now supports optional date filtering for specific financial periods.
     * * @param startDate Optional: Start of the period (YYYY-MM-DD)
     * @param endDate   Optional: End of the period (YYYY-MM-DD)
     */
    @GetMapping("/income-statement")
    public ResponseEntity<IncomeStatementDTO> getIncomeStatement(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        // If no dates are provided, the service will return the cumulative P&L
        IncomeStatementDTO report = incomeStatementService.getIncomeStatement(startDate, endDate);
        
        return ResponseEntity.ok(report);
    }

    /**
     * HEALTH CHECK ENDPOINT
     * Useful for Postman testing to ensure the reporting engine is online.
     */
    @GetMapping("/status")
    public ResponseEntity<String> getReportStatus() {
        return ResponseEntity.ok("Reporting Engine is Live. Connected to PostgreSQL: erp_finance");
    }
}