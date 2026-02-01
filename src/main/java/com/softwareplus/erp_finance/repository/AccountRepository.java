package com.softwareplus.erp_finance.repository;

import com.softwareplus.erp_finance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}