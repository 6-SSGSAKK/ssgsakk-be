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
    private Long productSeq; //상품ID
    private String purchaseProductName; //주문상품명
    private String purchaseProductVendor; //주문상품 벤더
    private Long productOptionSeq;;//주문상품 옵션
    private Integer purchaseProductCount;// 주문상품수량
    private Integer purchaseProductPrice; //주문상품금액
    private String purchaseProductOptionName;//주문상품옵션상세사항
    private Integer purchaseProductDiscountPrice; //주문상품할인인
    private String productThumbnail; //주문상품이미지
    private DeliveryType deliveryType; //주문상품배송타입
    private String productState; //주문상품 주문배송상태




    @Builder
    public PurchaseProductDto(Purchase purchaseSeq, Long productSeq, String purchaseProductName, String purchaseProductVendor,
                              Long productOptionSeq, Integer purchaseProductCount, Integer purchaseProductPrice, Integer purchaseProductDiscountPrice,
                              String purchaseProductOptionName, String productThumbnail, DeliveryType deliveryType, String productState) {


        this.purchaseSeq = purchaseSeq;
        this.productSeq = productSeq;
        this.purchaseProductName = purchaseProductName;
        this.purchaseProductVendor = purchaseProductVendor;
        this.productOptionSeq = productOptionSeq;
        this.purchaseProductCount = purchaseProductCount;
        this.purchaseProductPrice = purchaseProductPrice;
        this.purchaseProductOptionName = purchaseProductOptionName;
        this.purchaseProductDiscountPrice = purchaseProductDiscountPrice;
        this.productThumbnail = productThumbnail;
        this.deliveryType = deliveryType;
        this.productState = productState;

    }

}
