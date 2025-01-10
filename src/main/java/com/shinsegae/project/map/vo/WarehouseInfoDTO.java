package com.shinsegae.project.map.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseInfoDTO {
    private int warehouseId;
    private String warehouseName;
    private String warehouseLocation;
    private String releaseDate;
}
