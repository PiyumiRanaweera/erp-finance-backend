package com.softwareplus.erp_finance.repository;

import com.softwareplus.erp_finance.model.ChartOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccount, Long> {
    // Custom query to find all sub-accounts for a parent
    List<ChartOfAccount> findByParentId(Long parentId);
    
    // Custom query to find accounts by type (Asset, Liability, etc.)
    List<ChartOfAccount> findByAccountType(String accountType);
}