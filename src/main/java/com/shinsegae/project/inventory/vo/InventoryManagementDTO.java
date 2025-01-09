package com.shinsegae.project.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryManagementDTO {
    private String itemClassification;
    private String productName;
    private String warehouseName;
    private String manufacturerName;
    private int price;
    private int stock;
}