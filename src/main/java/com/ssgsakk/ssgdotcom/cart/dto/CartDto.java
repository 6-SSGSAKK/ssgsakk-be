package com.ssgsakk.ssgdotcom.cart.dto;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartDto {
        private Long cartSeq;
        private Short checkbox;
        private Short fixItem;
        private Long optionAndStockSeq;
        private Integer quantity;
        private Long productSeq;
        public static CartDto VoToDto(CartRequestVo cartRequestVo) {
            return CartDto.builder()
                    .optionAndStockSeq(cartRequestVo.getOptionAndStockSeq())
                    .quantity(cartRequestVo.getQuantity())
                    .productSeq(cartRequestVo.getProductSeq())
                    .build();
        }
        public static CartDto EntityToDto(Cart cart) {
            return CartDto.builder()
                    .cartSeq(cart.getCartSeq())
                    .checkbox(cart.getCheckbox())
                    .fixItem(cart.getFixItem())
                    .optionAndStockSeq(cart.getOptionAndStock().getOptionAndStockSeq())
                    .quantity(cart.getQuantity())
                    .productSeq(cart.getProduct().getProductSeq())
                    .build();
        }
}
