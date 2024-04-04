package com.ssgsakk.ssgdotcom.product.vo;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import com.ssgsakk.ssgdotcom.product.dto.ProductListInfoDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductListInfoResponseVo {
    private String productName;
    private Integer productPrice;
    private String vendor;
    private Integer discountPercent;
    private DeliveryType deliveryType;
    private Double averageRating;
    private Integer reviewCount;
    private ProductContentsVo contentsUrl;

    public static ProductListInfoResponseVo VoToDto(ProductListInfoDto productListInfoDto) {
        return ProductListInfoResponseVo.builder()
                .productName(productListInfoDto.getProductName())
                .productPrice(productListInfoDto.getProductPrice())
                .vendor(productListInfoDto.getVendor())
                .discountPercent(productListInfoDto.getDiscountPercent())
                .deliveryType(productListInfoDto.getDeliveryType())
                .averageRating(productListInfoDto.getAverageRating())
                .reviewCount(productListInfoDto.getReviewCount())
                .contentsUrl(productListInfoDto.getContentsUrl())
                .build();
    }
}
