package com.ssgsakk.ssgdotcom.option.dto;

import com.ssgsakk.ssgdotcom.option.domain.CustomizationOption;
import lombok.*;

@Builder
@Getter
public class OptionAndStockDto {
    private Long optionAndStockSeq;
    private String size;
    private String color;
    private CustomizationOption customizationOption;
    private Integer stock;
    private Integer minimumStock;
}
