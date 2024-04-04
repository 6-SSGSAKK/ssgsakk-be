package com.ssgsakk.ssgdotcom.purchase.dto;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PurchaseAndPurchaseProductDto {


    private String purchaser; //주문자이름
    private String purchaseCode; //주문코드
    private String purchaseUuid; //UUID
    private String purchaserPhoneNum; //주문자 전화번호
    private String purchaseEmail; //주문자 이메일
    private String recipient; //수령자 이름
    private String recipientPhoneNum; //수령자 전화번호
    private String recipientEmail; //수령자 이메일
    private String address; //최종배송지우편번호
    private String purchaseRoadAddress; //최종배송지 도로명주소
    private String purchaseJibunAddress; //최종배송지지번주소
    private String purchaseDetailAddress; //최종배송지상세주소
    private String deliverymessage; //배송메시지
    private Long shippingFee; //배송비
    private Long purchaseProductSeq; //주문상품ID
    private Long productId; //상품ID
    private String purchaseProductName; //주문상품명
    private String purchaseProductVendor; //주문상품 벤더
    private String purchaseProductOption;//주문상품 옵션
    private Integer purchaseProductCount;// 주문상품수량
    private Integer purchaseProductPrice; //주문상품금액
    private Integer purchaseProductDiscountPrice; //주문상품할인액
    private String productThumbnail; //주문상품이미지
    private DeliveryType productDeliveryType; //주문상품배송타입
    private String productState; //주문상품 주문배송상태

    @Builder
    public PurchaseAndPurchaseProductDto(String purchaser, String purchaseCode, String purchaseUuid, String purchaserPhoneNum,
                                         String purchaseEmail, String recipient, String recipientPhoneNum, String recipientEmail,
                                         String address, String purchaseRoadAddress, String purchaseJibunAddress, String purchaseDetailAddress,
                                         String deliverymessage, Long shippingFee, Long purchaseProductSeq, Long productId, String purchaseProductName,
                                         String purchaseProductVendor, String purchaseProductOption, Integer purchaseProductCount, Integer purchaseProductPrice,
                                         Integer purchaseProductDiscountPrice, String productThumbnail, DeliveryType productDeliveryType, String productState) {

        this.purchaser = purchaser;
        this.purchaseCode = purchaseCode;
        this.purchaseUuid = purchaseUuid;
        this.purchaserPhoneNum = purchaserPhoneNum;
        this.purchaseEmail = purchaseEmail;
        this.recipient = recipient;
        this.recipientPhoneNum = recipientPhoneNum;
        this.recipientEmail = recipientEmail;
        this.address = address;
        this.purchaseRoadAddress = purchaseRoadAddress;
        this.purchaseJibunAddress = purchaseJibunAddress;
        this.purchaseDetailAddress = purchaseDetailAddress;
        this.deliverymessage = deliverymessage;
        this.shippingFee = shippingFee;
        this.purchaseProductSeq = purchaseProductSeq;
        this.productId = productId;
        this.purchaseProductName = purchaseProductName;
        this.purchaseProductVendor = purchaseProductVendor;
        this.purchaseProductOption = purchaseProductOption;
        this.purchaseProductCount = purchaseProductCount;
        this.purchaseProductPrice = purchaseProductPrice;
        this.purchaseProductDiscountPrice = purchaseProductDiscountPrice;
        this.productThumbnail = productThumbnail;
        this.productDeliveryType = productDeliveryType;
        this.productState = productState;
    }

}
