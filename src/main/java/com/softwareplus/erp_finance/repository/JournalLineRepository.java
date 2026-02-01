package com.softwareplus.erp_finance.repository;

import com.softwareplus.erp_finance.model.JournalLine;
import com.softwareplus.erp_finance.dto.GeneralLedgerDTO;
import com.softwareplus.erp_finance.dto.TrialBalanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JournalLineRepository extends JpaRepository<JournalLine, Long> {

    // --- 1. Generic Pattern Sums ---
    @Query("SELECT COALESCE(SUM(j.debit), 0.0) FROM JournalLine j WHERE j.accountCode LIKE :codePattern")
    Double sumDebitsByCodePattern(@Param("codePattern") String codePattern);

    @Query("SELECT COALESCE(SUM(j.credit), 0.0) FROM JournalLine j WHERE j.accountCode LIKE :codePattern")
    Double sumCreditsByCodePattern(@Param("codePattern") String codePattern);

    // --- 2. Dashboard KPIs ---
    @Query("SELECT COALESCE(SUM(j.credit) - SUM(j.debit), 0.0) FROM JournalLine j " +
           "WHERE j.accountCode LIKE '4%' " +
           "AND (:start IS NULL OR j.journalEntry.entryDate >= :start) " +
           "AND (:end IS NULL OR j.journalEntry.entryDate <= :end)")
    Double calculateTotalRevenue(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT COALESCE(SUM(j.debit) - SUM(j.credit), 0.0) FROM JournalLine j WHERE j.accountCode = '1000'")
    Double calculateCashBalance();

    @Query("SELECT COALESCE(SUM(j.debit) - SUM(j.credit), 0.0) FROM JournalLine j WHERE j.accountCode = '1200'")
    Double calculateAccountsReceivable();

    @Query("SELECT COALESCE(SUM(j.credit) - SUM(j.debit), 0.0) FROM JournalLine j WHERE j.accountCode = '2000'")
    Double calculateAccountsPayable();

    // --- 3. Income Statement Calculations ---
    @Query("SELECT COALESCE(SUM(j.debit) - SUM(j.credit), 0.0) FROM JournalLine j " +
           "WHERE j.accountCode LIKE '5%' " +
           "AND (:start IS NULL OR j.journalEntry.entryDate >= :start) " +
           "AND (:end IS NULL OR j.journalEntry.entryDate <= :end)")
    Double calculateTotalExpenses(@Param("start") LocalDate start, @Param("end") LocalDate end);

    /**
     * FIXED: Changed a.name to a.accountName and joined on a.accountCode
     */
    @Query("SELECT j.accountCode, a.accountName, SUM(j.debit), SUM(j.credit) FROM JournalLine j " +
           "JOIN Account a ON j.accountCode = a.accountCode " +
           "WHERE (j.accountCode LIKE '4%' OR j.accountCode LIKE '5%') " +
           "AND (:start IS NULL OR j.journalEntry.entryDate >= :start) " +
           "AND (:end IS NULL OR j.journalEntry.entryDate <= :end) " +
           "GROUP BY j.accountCode, a.accountName")
    List<Object[]> getIncomeStatementDetails(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // --- 4. DTO Projections ---
    @Query("SELECT new com.softwareplus.erp_finance.dto.GeneralLedgerDTO(" +
           "j.id, e.entryDate, e.description, e.description, j.accountCode, j.debit, j.credit) " +
           "FROM JournalLine j JOIN j.journalEntry e " +
           "ORDER BY e.entryDate DESC, j.id DESC")
    List<GeneralLedgerDTO> findAllLedgerEntries();

    /**
     * FIXED: Changed a.name to a.accountName and joined on a.accountCode
     */
    @Query("SELECT new com.softwareplus.erp_finance.dto.TrialBalanceDTO(" +
           "jl.accountCode, " +
           "a.accountName, " + 
           "SUM(jl.debit), " +
           "SUM(jl.credit), " +
           "(SUM(jl.debit) - SUM(jl.credit))) " +
           "FROM JournalLine jl " +
           "JOIN Account a ON jl.accountCode = a.accountCode " + 
           "GROUP BY jl.accountCode, a.accountName")
    List<TrialBalanceDTO> getTrialBalance();
}