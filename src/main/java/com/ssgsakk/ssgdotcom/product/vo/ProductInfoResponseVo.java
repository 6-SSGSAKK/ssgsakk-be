package com.ssgsakk.ssgdotcom.product.vo;

import com.ssgsakk.ssgdotcom.common.utils.DeliveryType;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
