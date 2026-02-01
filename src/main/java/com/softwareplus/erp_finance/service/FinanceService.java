package com.softwareplus.erp_finance.service;

import com.softwareplus.erp_finance.model.JournalEntry;
import com.softwareplus.erp_finance.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate; // Changed from LocalDateTime
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveJournalEntry(JournalEntry entry) {
        if (entry.getEntryDate() == null) {
            // Fix: Use LocalDate.now() to match the Model
            entry.setEntryDate(LocalDate.now()); 
        }
        if (entry.getStatus() == null) {
            entry.setStatus("POSTED");
        }
        if (entry.getLines() != null) {
            entry.getLines().forEach(line -> line.setJournalEntry(entry));
        }
        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }
}