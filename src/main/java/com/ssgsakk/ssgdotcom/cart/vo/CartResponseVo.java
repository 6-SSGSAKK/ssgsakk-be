package com.ssgsakk.ssgdotcom.cart.vo;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CartResponseVo {
    private Long cartSeq;
    private Integer checkbox;
    private Integer fixItem;
    private Long optionAndStockSeq;
    private Integer quantity;
    private Long productSeq;
    public static List<CartResponseVo> DtoListToVoList(List<CartDto> cartDtoList) {
        return cartDtoList.stream().map(cartDto -> CartResponseVo.builder()
                .cartSeq(cartDto.getCartSeq())
                .checkbox(cartDto.getCheckbox())
                .fixItem(cartDto.getFixItem())
                .optionAndStockSeq(cartDto.getOptionAndStockSeq())
                .quantity(cartDto.getQuantity())
                .productSeq(cartDto.getProductSeq())
                .build())
                .collect(Collectors.toList());
    }
    public static CartResponseVo DtoToVo(CartDto cartDto) {
        return CartResponseVo.builder()
                .cartSeq(cartDto.getCartSeq())
                .checkbox(cartDto.getCheckbox())
                .fixItem(cartDto.getFixItem())
                .optionAndStockSeq(cartDto.getOptionAndStockSeq())
                .quantity(cartDto.getQuantity())
                .productSeq(cartDto.getProductSeq())
                .build();
    }
}
