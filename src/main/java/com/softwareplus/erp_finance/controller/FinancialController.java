package com.yourcompany.erp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3001") // This fixes the CORS error
public class FinancialController {

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

    // Mock data for Invoices (Accounts Payable)
    @GetMapping("/invoices")
    public List<Map<String, Object>> getInvoices() {
        return Arrays.asList(
            Map.of("invoiceNo", "INV-904", "vendor", "TechCorp", "amount", 4500.0, "status", "UNPAID")
        );
    }

    @PostMapping("/journals")
    public Map<String, Object> createJournalEntry(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Journal Entry: " + payload);
    
    // In a real app, you would use a Repository to save to a database here
    // journalRepository.save(new Journal(payload));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success"); // Use .put() for Maps
        response.put("message", "Journal Entry Posted Successfully");
        return response;
}
}
