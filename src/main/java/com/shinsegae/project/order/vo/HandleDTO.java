package com.shinsegae.project.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleDTO {
    private int outputId;
    private String actionType;
    private int releaseQuantity;
    private String productName;
}
