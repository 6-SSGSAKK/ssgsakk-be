package com.ssgsakk.ssgdotcom.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productSeq;
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
}