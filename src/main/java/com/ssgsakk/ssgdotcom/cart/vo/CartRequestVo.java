package com.ssgsakk.ssgdotcom.cart.vo;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CartRequestVo {
    private Long optionAndStockSeq;
    private Integer quantity;
    private Long productSeq;
    private Short checkbox;
    private Short fixItem;
}
