package com.ssgsakk.ssgdotcom.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private Integer productPrice;
    private String vendor;
    private Date createdAt;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
    private Double averageRating;
    private Integer reviewCount;
}
