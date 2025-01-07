package com.shinsegae.project.common.dataset.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufactureVO {
    private String manufacturer_code;
    private String manufacturer_name;
    private String sorting;
    private int license_number;

}