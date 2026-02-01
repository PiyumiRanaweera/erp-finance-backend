package com.softwareplus.erp_finance.service;

import com.softwareplus.erp_finance.dto.LineDTO;
import com.softwareplus.erp_finance.model.JournalEntry;
import com.softwareplus.erp_finance.model.JournalLine;
import com.softwareplus.erp_finance.repository.JournalEntryRepository;
import com.softwareplus.erp_finance.repository.JournalLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalService {

    @Autowired 
    private JournalEntryRepository entryRepo;
    
    @Autowired 
    private JournalLineRepository lineRepo;

    /**
     * Standard save method used by the REST Controller.
     * Links the Lines to the Header automatically and ensures data integrity.
     */
    @Transactional
    public JournalEntry saveEntry(JournalEntry entry) {
        // 1. Set System Entry Date if missing
        if (entry.getEntryDate() == null) {
            entry.setEntryDate(LocalDate.now());
        }

        // 2. Set Transaction Date to today if the user left it blank
        if (entry.getTransactionDate() == null) {
            entry.setTransactionDate(LocalDate.now());
        }

        // 3. Link Lines and Sanitize amounts
        if (entry.getLines() != null) {
            for (JournalLine line : entry.getLines()) {
                // Ensure the Foreign Key relationship is set
                line.setJournalEntry(entry);
                
                // Ensure no null values are saved for numeric columns
                if (line.getDebit() == 0.0) line.setDebit(0.0);
                if (line.getCredit() == 0.0) line.setCredit(0.0);
            }
        }
        
        // Save the header (Cascade will save the lines)
        return entryRepo.save(entry);
    }

    /**
     * Manual post method used when converting from DTOs.
     */
    @Transactional
    public void postToLedger(String reference, String description, List<LineDTO> lines) {
        JournalEntry header = new JournalEntry();
        header.setDescription(description);
        header.setEntryDate(LocalDate.now());
        header.setTransactionDate(LocalDate.now()); 
        header.setStatus("POSTED");
        
        // Save header first to generate an ID
        JournalEntry savedHeader = entryRepo.save(header);

        for (LineDTO line : lines) {
            JournalLine jl = new JournalLine();
            jl.setAccountCode(line.getAccountCode());
            jl.setDebit(line.getDebit() != null ? line.getDebit() : 0.0);
            jl.setCredit(line.getCredit() != null ? line.getCredit() : 0.0);
            
            // Link the line to the saved header
            jl.setJournalEntry(savedHeader); 
            lineRepo.save(jl);
        }
    }

    /**
     * Deletes a Journal Entry and all its associated lines.
     */
    @Transactional
    public void deleteEntry(Long id) {
        if (entryRepo.existsById(id)) {
            entryRepo.deleteById(id);
        } else {
            throw new RuntimeException("Entry with ID " + id + " not found.");
        }
    }
    
    /**
     * Retrieves all entries for the Ledger display.
     */
    public List<JournalEntry> getAllJournalEntries() {
        return entryRepo.findAll();
    }
}