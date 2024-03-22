package com.ssgsakk.ssgdotcom.product.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchProductResponseVo {
    private Long productSeq;
    private String productName;
    private Integer productPrice;
}