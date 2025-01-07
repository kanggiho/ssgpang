package com.shinsegae.project.common.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseVO {
   private int warehouse_id;
   private String warehouse_name;
   private String warehouse_location;
   private String warehouse_temperature;


}
