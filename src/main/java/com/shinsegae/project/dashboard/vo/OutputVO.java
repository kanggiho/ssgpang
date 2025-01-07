package com.shinsegae.project.dashboard.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputVO {
   private int product_code;
   private int warehouse_id;
   private int user_id;
   private int confirm_num;
   private int confirm_id;
   private String status;
   private int unit_price;
   private int release_quantity;
   private String release_date;


}
