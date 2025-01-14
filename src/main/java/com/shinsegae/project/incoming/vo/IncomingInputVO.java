package com.shinsegae.project.incoming.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingInputVO {
   private int inputNum;
   private String manufacturerCode;
   private int productCode;
   private int warehousedQuantity;
   private String warehousedDate;
   // inventory 삽입을 위한 필드 추가
   private String code;
   private int warehouseId;
   private int price;
}

