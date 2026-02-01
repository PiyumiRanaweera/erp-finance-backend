// src/main/java/com/softwareplus/erp_finance/dto/JournalRequest.java
package com.softwareplus.erp_finance.dto;

import lombok.Data;
import java.util.List;

@Data
public class JournalRequest {
    private String description;
    private String date;
    private List<LineDTO> entries;
}