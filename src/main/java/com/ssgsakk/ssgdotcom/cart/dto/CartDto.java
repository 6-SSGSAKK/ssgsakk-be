package com.ssgsakk.ssgdotcom.cart.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartDto {
        private Long optionAndStockId;
        private Integer quantity;
        private Integer productSeq;
}
