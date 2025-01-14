package com.shinsegae.project.excel.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelInventoryVO {
    private String code;
    private int productCode;
    private String manufacturerCode;
    private int warehouseId;
    private int price;
    private int stock;

}