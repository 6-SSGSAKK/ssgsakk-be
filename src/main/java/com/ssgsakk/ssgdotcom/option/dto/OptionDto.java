package com.ssgsakk.ssgdotcom.option.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OptionDto {
    private Integer depthLevel;
    private String firstDepthName;
    private String secondDepthName;
    private String thirdDepthName;
    private List<StockDto> options;
}
