package com.ssgsakk.ssgdotcom.option.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateOptionDto {
    private Long productSeq;
    private Long sizeSeq;
    private Long colorSeq;
    private Long customizationOptionSeq;
    private Integer stock;
    private Integer minimumStock;
}
