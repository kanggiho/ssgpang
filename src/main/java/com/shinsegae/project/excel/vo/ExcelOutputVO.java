package com.shinsegae.project.excel.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelOutputVO {
    private int outputId;
    private int productCode;
    private int warehouseId;
    private int userId;
    private int confirmNum;
    private int confirmId;
    private String status;
    private int unitPrice;
    private int releaseQuantity;
    private String releaseDate;
}