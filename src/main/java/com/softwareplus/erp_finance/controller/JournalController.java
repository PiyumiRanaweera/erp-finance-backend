package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.model.JournalEntry;
import com.softwareplus.erp_finance.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
// Ensure this matches your React dev server port
@CrossOrigin(origins = "http://localhost:3000") 
public class JournalController {

    @Autowired
    private JournalService journalService; 

    /**
     * Fetches all journal entries for the General Ledger view.
     */
    @GetMapping
    public List<JournalEntry> getAllJournals() {
        return journalService.getAllJournalEntries();
    }

    /**
     * Receives a new journal entry from the React frontend.
     * Includes double-entry validation on the server side.
     */
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) {
        // 1. Server-Side Validation: Ensure Debits = Credits
        // This is a safety net in case the frontend validation is bypassed.
        double totalDebit = entry.getLines().stream()
                .mapToDouble(l -> l.getDebit() != 0.0 ? l.getDebit() : 0.0).sum();
        double totalCredit = entry.getLines().stream()
                .mapToDouble(l -> l.getCredit() != 0.0 ? l.getCredit() : 0.0).sum();

        if (Math.abs(totalDebit - totalCredit) > 0.01) {
            return ResponseEntity.badRequest().body("Validation Failed: Journal entry is not balanced.");
        }

        if (entry.getLines().size() < 2) {
            return ResponseEntity.badRequest().body("Validation Failed: At least two line items are required.");
        }

        try {
            // 2. Save via Service to handle @Transactional logic
            JournalEntry savedEntry = journalService.saveEntry(entry);
            return ResponseEntity.ok(savedEntry);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Database Error: " + e.getMessage());
        }
    }

    /**
     * Deletes an entry and its associated lines.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long id) {
        try {
            journalService.deleteEntry(id);
            return ResponseEntity.ok().body("Entry #" + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error: Entry not found.");
        }
    }
}