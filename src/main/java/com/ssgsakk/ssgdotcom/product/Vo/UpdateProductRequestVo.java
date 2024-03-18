package com.ssgsakk.ssgdotcom.product.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestVo {
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
}
