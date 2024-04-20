package com.ssgsakk.ssgdotcom.purchaseproduct.dto;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseProductListDto { //회원주문상품상세 조회 내역을 담는 DTO

    private Long purchaseProductSeq; //주문상품ID
    private Purchase purchaseSeq; //주문ID
    private Long productSeq; //상품ID
    private String purchaseProductName; //주문상품명
    private String purchaseProductVendor; //주문상품 벤더
    private Long productOptionSeq;//상품옵션 번호
    private Integer purchaseProductCount;// 주문상품수량
    private String purchaseProductOptionName;//주문상품옵션사항
    private Integer purchaseProductPrice; //주문상품금액
    private Integer purchaseProductDiscountPrice; //주문상품할인액
    private String productThumbnail; //주문상품이미지
    private String deliveryType ; //주문상품배송타입
    private Integer productState; //주문상품 주문배송상태



    @Builder
    public PurchaseProductListDto(Long purchaseProductSeq, Purchase purchaseSeq, Long productSeq,
                                  String purchaseProductName, String purchaseProductVendor,
                                  Long productOptionSeq, Integer purchaseProductCount, String purchaseProductOptionName,
                                  Integer purchaseProductPrice, Integer purchaseProductDiscountPrice, String productThumbnail,
                                  String deliveryType, Integer productState) {

        this.purchaseProductSeq = purchaseProductSeq;
        this.purchaseSeq = purchaseSeq;
        this.productSeq = productSeq;
        this.purchaseProductName = purchaseProductName;
        this.purchaseProductVendor = purchaseProductVendor;
        this.productOptionSeq = productOptionSeq;
        this.purchaseProductCount = purchaseProductCount;
        this.purchaseProductOptionName = purchaseProductOptionName;
        this.purchaseProductPrice = purchaseProductPrice;
        this.purchaseProductDiscountPrice = purchaseProductDiscountPrice;
        this.productThumbnail = productThumbnail;
        this.deliveryType = deliveryType;
        this.productState = productState;

    }
}
