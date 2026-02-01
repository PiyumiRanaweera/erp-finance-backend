package com.softwareplus.erp_finance.service;

import com.softwareplus.erp_finance.dto.LineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsPayableService {

    @Autowired
    private JournalService journalService;

    public void payVendorInvoice(Long invoiceId, Double amount) {
        // Logic to update invoice status in the database would go here
        
        // Prepare the accounting entry for the General Ledger
        List<LineDTO> lines = new ArrayList<>();
        
        // Debit Accounts Payable (Reducing what we owe)
        lines.add(new LineDTO("2100", "Accounts Payable", amount, 0.0, "Payment for Invoice #" + invoiceId));
        
        // Credit Cash (Reducing our money)
        lines.add(new LineDTO("1010", "Cash", 0.0, amount, "Payment for Invoice #" + invoiceId));

        journalService.postToLedger("PAY-" + invoiceId, "Vendor Payment Processed", lines);
    }
}