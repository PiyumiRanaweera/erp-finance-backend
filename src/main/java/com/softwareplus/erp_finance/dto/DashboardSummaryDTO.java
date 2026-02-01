package com.softwareplus.erp_finance.dto;

public class DashboardSummaryDTO {
    private Double totalRevenue;
    private Double accountsReceivable;
    private Double accountsPayable;
    private Double cashBalance;
    private Double revenueChangePercent;

    // 1. MUST HAVE: No-args constructor for JSON serialization
    public DashboardSummaryDTO() {}

    // 2. Main constructor for your Service to use
    public DashboardSummaryDTO(Double totalRevenue, Double accountsReceivable, Double accountsPayable, Double cashBalance, Double revenueChangePercent) {
        this.totalRevenue = totalRevenue;
        this.accountsReceivable = accountsReceivable;
        this.accountsPayable = accountsPayable;
        this.cashBalance = cashBalance;
        this.revenueChangePercent = revenueChangePercent;
    }

    // 3. Getters and Setters (Required for Spring to "see" the data)
    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }

    public Double getAccountsReceivable() { return accountsReceivable; }
    public void setAccountsReceivable(Double accountsReceivable) { this.accountsReceivable = accountsReceivable; }

    public Double getAccountsPayable() { return accountsPayable; }
    public void setAccountsPayable(Double accountsPayable) { this.accountsPayable = accountsPayable; }

    public Double getCashBalance() { return cashBalance; }
    public void setCashBalance(Double cashBalance) { this.cashBalance = cashBalance; }

    public Double getRevenueChangePercent() { return revenueChangePercent; }
    public void setRevenueChangePercent(Double revenueChangePercent) { this.revenueChangePercent = revenueChangePercent; }
}