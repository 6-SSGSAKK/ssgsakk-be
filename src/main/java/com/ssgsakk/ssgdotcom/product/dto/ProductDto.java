package com.ssgsakk.ssgdotcom.product.dto;

import com.ssgsakk.ssgdotcom.common.utils.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productSeq;
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productDescription;
    private Integer discountPercent;
    private DeliveryType deliveryType;
    private Double averageRating;
    private Integer reviewCount;
    private String contentsUrl;
}