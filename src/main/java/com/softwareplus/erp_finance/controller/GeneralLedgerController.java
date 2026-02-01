package com.softwareplus.erp_finance.controller;

import com.softwareplus.erp_finance.dto.GeneralLedgerDTO; // Import your DTO
import com.softwareplus.erp_finance.repository.JournalLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/finance/ledger")
@CrossOrigin(origins = "http://localhost:3000")
public class GeneralLedgerController {

    @Autowired
    private JournalLineRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<GeneralLedgerDTO>> getFullLedger() {
        return ResponseEntity.ok(repository.findAllLedgerEntries());
    }
}