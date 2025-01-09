package com.shinsegae.project.order.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementDTO {
    private String productName;
    private String id;
    private int unitPrice;
    private int releaseQuantity;
    private String releaseDate;
    private int confirmNum;
    private int confirmId;
    private String status;
}
