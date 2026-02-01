package com.softwareplus.erp_finance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class JournalLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountCode;
    private String accountCategory; // NEW: e.g., Asset, Liability, Income, Expense
    private Double debit;
    private Double credit;

    @ManyToOne
    @JoinColumn(name = "journal_entry_id")
    @JsonBackReference
    private JournalEntry journalEntry;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccountCode() { return accountCode; }
    public void setAccountCode(String accountCode) { this.accountCode = accountCode; }
    public String getAccountCategory() { return accountCategory; }
    public void setAccountCategory(String accountCategory) { this.accountCategory = accountCategory; }
    public double getDebit() { return debit; }
    public void setDebit(double debit) { this.debit = debit; }
    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    public JournalEntry getJournalEntry() { return journalEntry; }
    public void setJournalEntry(JournalEntry journalEntry) { this.journalEntry = journalEntry; }
}