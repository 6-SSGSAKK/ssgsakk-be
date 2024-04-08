package com.ssgsakk.ssgdotcom.cart.vo;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CartRequestVo {
    private Long optionAndStockSeq;
    private Integer quantity;
    private Long productSeq;
    private Integer checkbox;
    private Integer fixItem;
}
