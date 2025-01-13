package com.shinsegae.project.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleDTO {
    private String orderId;
    private String actionType;
}
