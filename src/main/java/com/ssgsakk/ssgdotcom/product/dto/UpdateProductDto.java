package com.ssgsakk.ssgdotcom.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
}