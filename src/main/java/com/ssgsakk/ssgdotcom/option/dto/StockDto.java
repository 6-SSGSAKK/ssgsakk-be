package com.ssgsakk.ssgdotcom.option.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class StockDto {
    private Long OptionAndStockSeq;
    private String explain;
    private String explain2;
    private String explain3;
    private Integer stock;
}
