package com.ssgsakk.ssgdotcom.option.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockRequestVo {
    private Long sizeSeq;
    private Long colorSeq;
    private Long customizationOptionSeq;
}
