package com.ssgsakk.ssgdotcom.purchaseproduct.dto;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class PurchaseProductDto {

    private Purchase purchaseSeq; //주문ID
    private Long productId; //상품ID
    private String purchaseProductName; //주문상품명
    private String purchaseProductVendor; //주문상품 벤더
    private String purchaseProductOption;//주문상품 옵션
    private Integer purchaseProductCount;// 주문상품수량
    private Integer purchaseProductPrice; //주문상품금액
    private Integer purchaseProductDiscountPrice; //주문상품할인인
    private String productThumbnail; //주문상품이미지
    private DeliveryType deliveryType; //주문상품배송타입
    private String productState; //주문상품 주문배송상태




    @Builder
    public PurchaseProductDto(Purchase purchaseSeq, Long productId, String purchaseProductName, String purchaseProductVendor,
                              String purchaseProductOption, Integer purchaseProductCount, Integer purchaseProductPrice, Integer purchaseProductDiscountPrice,
                              String productThumbnail, DeliveryType deliveryType, String productState) {


        this.purchaseSeq = purchaseSeq;
        this.productId = productId;
        this.purchaseProductName = purchaseProductName;
        this.purchaseProductVendor = purchaseProductVendor;
        this.purchaseProductOption = purchaseProductOption;
        this.purchaseProductCount = purchaseProductCount;
        this.purchaseProductPrice = purchaseProductPrice;
        this.purchaseProductDiscountPrice = purchaseProductDiscountPrice;
        this.productThumbnail = productThumbnail;
        this.deliveryType = deliveryType;
        this.productState = productState;

    }

}
