package com.ssgsakk.ssgdotcom.product.vo;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;

import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Builder
@Getter
public class ProductInfoResponseVo {
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productDescription;
    private Integer discountPercent;
    private DeliveryType deliveryType;
    private Double averageRating;
    private Integer reviewCount;
    private List<ProductContentsVo> contents;

    public static ProductInfoResponseVo VoToDto(ProductDto productDto) {
        return ProductInfoResponseVo.builder()
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .vendor(productDto.getVendor())
                .productDescription(productDto.getProductDescription())
                .discountPercent(productDto.getDiscountPercent())
                .deliveryType(productDto.getDeliveryType())
                .averageRating(productDto.getAverageRating())
                .reviewCount(productDto.getReviewCount())
                .contents(productDto.getContentsUrl())
                .build();
    }
}
