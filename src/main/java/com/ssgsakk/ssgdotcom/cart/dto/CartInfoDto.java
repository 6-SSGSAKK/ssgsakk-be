package com.ssgsakk.ssgdotcom.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartInfoDto {
    private Long cartSeq;
    private Long productSeq;
    private String productOption;
    private Long OptionAndStockSeq;
    private Integer stock;
    private String productVendor;
    private Integer productPrice;
    private String productName;
    private Integer quantity;
    private String productImage;
    private Integer discountPercent;
    private Integer fixItem;
    private Integer checkbox;
}
