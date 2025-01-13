package com.shinsegae.project.dashboard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodayInputDTO {
    private int inputNum;
    private String manufacturerName;
    private int productCode;
    private String productName;
    private int warehousedQuantity;
}
