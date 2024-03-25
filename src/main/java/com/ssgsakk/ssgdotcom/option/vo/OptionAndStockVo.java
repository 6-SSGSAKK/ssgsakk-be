package com.ssgsakk.ssgdotcom.option.vo;

import com.ssgsakk.ssgdotcom.option.domain.CustomizationOption;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OptionAndStockVo {
    private Long productSeq;
    private String size;
    private String color;
    private CustomizationOption customizationOption;
    private Integer stock;
    private Integer minimumStock;
}
