package com.shinsegae.project.inventory.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryVO {
   private String code;
   private int product_code;
   private String manufacturer_code;
   private int warehouse_id;
   private int price;
   private int stock;

}
