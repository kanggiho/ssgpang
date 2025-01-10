package com.shinsegae.project.dashboard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBarChartPriceDTO {
    private String releaseMonth;
    private int releasePrice;
}