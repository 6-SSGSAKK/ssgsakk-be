package com.ssgsakk.ssgdotcom.likes.vo;

import com.ssgsakk.ssgdotcom.likes.dto.ContentsUrl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserProductLikesResponseVo {
    private String productName;
    private Integer productPrice;
    private Integer discountPercent;
    private String vendor;
    private String deliveryType;
    private List<ContentsUrl> contents;

    public UserProductLikesResponseVo(String productName, Integer productPrice, Integer discountPercent, String vendor, String deliveryType) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPercent = discountPercent;
        this.vendor = vendor;
        this.deliveryType = deliveryType;
    }

    public UserProductLikesResponseVo(String productName, Integer productPrice, Integer discountPercent, String vendor, String deliveryType, List<ContentsUrl> contents) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPercent = discountPercent;
        this.vendor = vendor;
        this.deliveryType = deliveryType;
        this.contents = contents;
    }
}
