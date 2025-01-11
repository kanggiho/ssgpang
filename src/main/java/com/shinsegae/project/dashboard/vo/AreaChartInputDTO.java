package com.shinsegae.project.dashboard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaChartInputDTO {
    private int warehousedQuantity;
    private String warehousedMonth;
}