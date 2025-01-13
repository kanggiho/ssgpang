package com.shinsegae.project.common.dataset.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseVO {
    private int warehouseId;
    private String warehouseName;
    private String warehouseLocation;
    private String warehouseTemperature;
}