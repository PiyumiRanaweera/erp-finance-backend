package com.yourcompany.erp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api")
// Ensures Vercel frontend can access the Railway backend
@CrossOrigin(origins = "https://erp-finance-frontend.vercel.app") 
public class FinancialController {

    /**
     * FIX: Missing Dashboard Summary Endpoint
     * This addresses the 404 error: /api/finance/dashboard/summary
     */
    @GetMapping("/finance/dashboard/summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalRevenue", 452500.0);
        summary.put("totalExpenses", 125000.0);
        summary.put("netIncome", 327500.0);
        summary.put("cashBalance", 52450.0);
        return ResponseEntity.ok(summary);
    }

    // Mock data for Accounts
    @GetMapping("/accounts")
    public List<Map<String, Object>> getAccounts() {
        return Arrays.asList(
            Map.of("id", 1, "name", "Cash at Bank", "type", "ASSET", "balance", 52450.0),
            Map.of("id", 2, "name", "Accounts Receivable", "type", "ASSET", "balance", 12500.0),
            Map.of("id", 3, "name", "Sales Revenue", "type", "INCOME", "balance", 452500.0)
        );
    }

    // Mock data for Ledger/Journals
    @GetMapping("/journals")
    public List<Map<String, Object>> getJournals() {
        return Arrays.asList(
            Map.of("id", 501, "date", "2026-01-12", "description", "Invoice INV-001 Payment", "amount", 1250.0),
            Map.of("id", 502, "date", "2026-01-13", "description", "Office Supplies", "amount", -450.0)
        );
    }

    // Handle Period Locking Status
    @GetMapping("/settings/is_period_locked")
    public Map<String, Boolean> getPeriodStatus() {
        return Map.of("isLocked", false);
    }

    // Mock data for Invoices
    @GetMapping("/invoices")
    public List<Map<String, Object>> getInvoices() {
        return Arrays.asList(
            Map.of("invoiceNo", "INV-904", "vendor", "TechCorp", "amount", 4500.0, "status", "UNPAID")
        );
    }

    @PostMapping("/journals")
    public Map<String, Object> createJournalEntry(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Journal Entry: " + payload);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Journal Entry Posted Successfully");
        return response;
    }
}