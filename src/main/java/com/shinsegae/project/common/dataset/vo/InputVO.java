package com.shinsegae.project.common.dataset.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputVO {
    private int input_num;
    private String manufacturer_code;
    private int product_code;
    private int warehoused_quantity;
    private String warehoused_date;
}