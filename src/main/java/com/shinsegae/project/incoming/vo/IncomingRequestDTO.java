package com.shinsegae.project.incoming.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingRequestDTO {
   private int productCode;
   private String productName;
   private String manufacturerName;
   private String warehousedQuantity;
   private String warehousedDate;
   private String manufacturerCode;
   private String item_classification;
   private String code;
   private int price;
   private int warehouseId;
}
