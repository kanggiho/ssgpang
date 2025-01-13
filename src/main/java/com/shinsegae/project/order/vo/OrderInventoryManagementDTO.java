package com.shinsegae.project.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInventoryManagementDTO {
    private String itemClassification;
    private String productName;
    private String warehouseName;
    private String manufacturerName;
    private int price;
    private int stock;
    private int productCode;
    private int warehouseId;
}