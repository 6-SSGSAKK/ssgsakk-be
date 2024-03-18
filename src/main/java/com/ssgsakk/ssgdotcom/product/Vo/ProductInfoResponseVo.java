package com.ssgsakk.ssgdotcom.product.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoResponseVo {
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
}
