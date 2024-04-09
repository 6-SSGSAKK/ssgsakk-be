package com.ssgsakk.ssgdotcom.cart.vo;

import com.ssgsakk.ssgdotcom.cart.dto.CartInfoDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartInfoResponseVo {
    private Long cartSeq;
    private Long productSeq;
    private String productOption;
    private Integer stock;
    private String productVendor;
    private Integer productPrice;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discountPercent;
    private Long OptionAndStockSeq;
    private Integer fixItem;
    private Integer checkbox;

    public static CartInfoResponseVo InfoDtoToVo(CartInfoDto cartInfoDto) {
        return CartInfoResponseVo.builder()
                .cartSeq(cartInfoDto.getCartSeq())
                .OptionAndStockSeq(cartInfoDto.getOptionAndStockSeq())
                .stock(cartInfoDto.getStock())
                .fixItem(cartInfoDto.getFixItem())
                .checkbox(cartInfoDto.getCheckbox())
                .productSeq(cartInfoDto.getProductSeq())
                .productOption(cartInfoDto.getProductOption())
                .productVendor(cartInfoDto.getProductVendor())
                .productPrice(cartInfoDto.getProductPrice())
                .productName(cartInfoDto.getProductName())
                .quantity(cartInfoDto.getQuantity())
                .productImage(cartInfoDto.getProductImage())
                .discountPercent(cartInfoDto.getDiscountPercent())
                .build();
    }
}
