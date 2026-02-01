package com.softwareplus.erp_finance.dto;

public class LineDTO {
    private String accountCode;
    private String accountName;
    private Double debit;
    private Double credit;
    private String description;

    // 1. MUST HAVE: No-args constructor for JSON Deserialization
    public LineDTO() {}

    // 2. All-args constructor (Good for testing)
    public LineDTO(String accountCode, String accountName, Double debit, Double credit, String description) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.debit = debit;
        this.credit = credit;
        this.description = description;
    }

    // 3. MUST HAVE: Standard Getters and Setters
    public String getAccountCode() { return accountCode; }
    public void setAccountCode(String accountCode) { this.accountCode = accountCode; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public Double getDebit() { return debit != 0.0 ? debit : 0.0; }
    public void setDebit(Double debit) { this.debit = debit; }

    public Double getCredit() { return credit != 0.0 ? credit : 0.0; }
    public void setCredit(Double credit) { this.credit = credit; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}