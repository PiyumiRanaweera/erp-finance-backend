package com.softwareplus.erp_finance.model;

import jakarta.persistence.Entity; // This is the missing "Entity" symbol
import jakarta.persistence.Id;     // This is the missing "Id" symbol
import jakarta.persistence.Table;

@Entity
@Table(name = "system_settings")
public class SystemSetting {

    @Id
    private String key;
    private String value;

    // Standard Getters and Setters
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
