package com.ssgsakk.ssgdotcom.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartInfoDto {
    private Long productSeq;
    private String productOption;
    private String productVendor;
    private Integer productPrice;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discountPercent;
}
