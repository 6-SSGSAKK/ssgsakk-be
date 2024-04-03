package com.ssgsakk.ssgdotcom.cart.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartDto {
        private Short checkbox;
        private Short fixItem;
        private Long optionAndStockId;
        private Integer quantity;
        private Long productSeq;
}
