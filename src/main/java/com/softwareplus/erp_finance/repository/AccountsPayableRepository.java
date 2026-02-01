package com.erp.finance.repository;

import com.erp.finance.model.AccountsPayable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsPayableRepository extends JpaRepository<AccountsPayable, Long> {
    // JpaRepository provides all CRUD methods: save(), findAll(), delete(), etc.
}