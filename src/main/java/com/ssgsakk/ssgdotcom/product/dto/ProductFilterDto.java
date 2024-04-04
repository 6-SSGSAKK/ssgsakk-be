package com.ssgsakk.ssgdotcom.product.dto;


import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import lombok.*;

@Builder
@Getter
public class ProductFilterDto {
    private Long categorySeq;
    private DeliveryType deliveryType;
    private Integer minPrice;
    private Integer maxPrice;
    private String keyword;

    public static ProductFilterDto ToDto(String keyword, DeliveryType deliveryType, Integer minPrice,
                                                       Integer maxPrice, Long categorySeq) {
        return ProductFilterDto.builder()
                .keyword(keyword)
                .deliveryType(deliveryType)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .categorySeq(categorySeq)
                .build();
    }

}
