package com.ssgsakk.ssgdotcom.product.dto;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class ProductListInfoDto {
    private Long productSeq;
    private String productName;
    private Integer productPrice;
    private String vendor;
    private Integer discountPercent;
    private DeliveryType deliveryType;
    private Double averageRating;
    private Integer reviewCount;
    private ProductContentsVo contentsUrl;
}
