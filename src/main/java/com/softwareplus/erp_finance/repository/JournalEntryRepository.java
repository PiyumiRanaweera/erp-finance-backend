package com.softwareplus.erp_finance.repository;

import com.softwareplus.erp_finance.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    // Find entries by status (e.g., all entries needing 'Approval')
    List<JournalEntry> findByStatus(String status);
}