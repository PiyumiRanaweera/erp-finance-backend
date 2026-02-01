package com.erp.finance.controller;

import com.erp.finance.model.AccountsPayable;
import com.erp.finance.repository.AccountsPayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ap")
@CrossOrigin(origins = "http://localhost:3000") // Connects to your React Frontend
public class AccountsPayableController {

    @Autowired
    private AccountsPayableRepository apRepository;

    // GET all invoices for the Accounts Payable table
    @GetMapping("/invoices")
    public List<AccountsPayable> getAllInvoices() {
        return apRepository.findAll();
    }

    // POST a new invoice (When you click "Add Invoice" in React)
    @PostMapping("/add")
    public AccountsPayable createInvoice(@RequestBody AccountsPayable invoice) {
        return apRepository.save(invoice);
    }
}