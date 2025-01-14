package com.shinsegae.project.excel.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelInputVO {
    private int inputNum;
    private String manufacturerCode;
    private int productCode;
    private int warehousedQuantity;
    private String warehousedDate;
}