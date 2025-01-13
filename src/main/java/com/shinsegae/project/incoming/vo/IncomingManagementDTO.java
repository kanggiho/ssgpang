package com.shinsegae.project.incoming.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingManagementDTO {
   private int inputNum;
   private String productName;
   private String manufacturerName;
   private String warehousedQuantity;
   private String warehousedDate;
}
