package com.shinsegae.project.order.vo;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String productName; // 상품명
    private int quantity;       // 수량
}