package com.ssgsakk.ssgdotcom.cart.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartRequestVo {
    private Long optionAndStockId;
    private Integer quantity;
    private Long productSeq;
}
