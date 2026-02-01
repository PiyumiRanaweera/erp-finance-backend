package com.softwareplus.erp_finance.config;

import com.softwareplus.erp_finance.model.JournalEntry;
import com.softwareplus.erp_finance.model.JournalLine;
import com.softwareplus.erp_finance.repository.JournalEntryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(JournalEntryRepository repository) {
        return args -> {
            try {
                if (repository.count() == 0) {
                    // --- Entry 1: Monthly Rent ---
                    JournalEntry rentEntry = new JournalEntry();
                    rentEntry.setDescription("January Office Rent");
                    rentEntry.setEntryDate(LocalDate.now());
                    // ADDED: Setting transactionDate to satisfy the Database Not-Null constraint
                    rentEntry.setTransactionDate(LocalDate.now()); 
                    rentEntry.setStatus("POSTED");

                    JournalLine line1 = new JournalLine();
                    line1.setAccountCode("6001 Rent Expense");
                    line1.setDebit(1200.00);
                    line1.setCredit(0.0);
                    line1.setJournalEntry(rentEntry);

                    JournalLine line2 = new JournalLine();
                    line2.setAccountCode("1001 Cash");
                    line2.setDebit(0.0);
                    line2.setCredit(1200.00);
                    line2.setJournalEntry(rentEntry);

                    rentEntry.setLines(Arrays.asList(line1, line2));
                    repository.save(rentEntry);

                    // --- Entry 2: Consulting Revenue ---
                    JournalEntry salesEntry = new JournalEntry();
                    salesEntry.setDescription("Consulting Service - Project X");
                    salesEntry.setEntryDate(LocalDate.now());
                    salesEntry.setTransactionDate(LocalDate.now()); // ADDED
                    salesEntry.setStatus("POSTED");

                    JournalLine line3 = new JournalLine();
                    line3.setAccountCode("1001 Cash");
                    line3.setDebit(2500.00);
                    line3.setCredit(0.0);
                    line3.setJournalEntry(salesEntry);

                    JournalLine line4 = new JournalLine();
                    line4.setAccountCode("4001 Service Revenue");
                    line4.setDebit(0.0);
                    line4.setCredit(2500.00);
                    line4.setJournalEntry(salesEntry);

                    salesEntry.setLines(Arrays.asList(line3, line4));
                    repository.save(salesEntry);

                    System.out.println(">>> Data Seeding Complete: Added sample Journal Entries.");
                }
            } catch (Exception e) {
                System.err.println(">>> Data Seeding Failed: " + e.getMessage());
            }
        };
    }
}