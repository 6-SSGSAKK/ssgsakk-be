package com.ssgsakk.ssgdotcom.purchaseproduct.domain;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseProductSeq; //주문상품ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseSeq")
    private Purchase purchaseSeq; //주문ID

    @Column(length = 50)
    private Long productSeq; //상품ID

    @Column(length = 50)
    private String purchaseProductName; //주문상품명

    @Column(length = 50)
    private String purchaseProductVendor; //주문상품 벤더

    @Column(length = 50)
    private Long productOptionSeq;//상품옵션 번호

    @Column(length = 50)
    private Integer purchaseProductCount;// 주문상품수량

    @Column(length = 50)
    private String purchaseProductOptionName;//주문상품옵션상세사항

    @Column(length = 50)
    private Integer purchaseProductPrice; //주문상품금액

    @Column(length = 50)
    private Integer purchaseProductDiscountPrice; //주문상품할인액

    @Column(length = 50)
    private String productThumbnail; //주문상품이미지

    @Column(length = 10)
    private String deliveryType ; //주문상품배송타입

    @Column(length = 50)
    private String productState; //주문상품 주문배송상태

    @Builder
    public PurchaseProduct(Long purchaseProductSeq, Purchase purchaseSeq, Long productSeq,
                           String purchaseProductName, String purchaseProductVendor,
                           Long productOptionSeq, Integer purchaseProductCount,
                           String purchaseProductOptionName, Integer purchaseProductPrice,
                           Integer purchaseProductDiscountPrice, String productThumbnail,
                           String deliveryType, String productState) {

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
