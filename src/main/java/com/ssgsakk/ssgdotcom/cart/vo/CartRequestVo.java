package com.ssgsakk.ssgdotcom.cart.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartRequestVo {
    private Short checkbox;
    private Short fixItem;
    private Long optionAndStockId;
    private Integer quantity;
    private Long productSeq;
}
