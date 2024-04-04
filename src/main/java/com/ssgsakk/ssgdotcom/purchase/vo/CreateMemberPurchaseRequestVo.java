package com.ssgsakk.ssgdotcom.purchase.vo;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMemberPurchaseRequestVo {

    private String purchaser; //주문자이름
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
    // 여기까지가 PurchaseDTO로

    private Long purchaseProductSeq; //주문상품ID
    private Long productId; //상품ID
    private String purchaseProductName; //주문상품명
    private String purchaseProductVendor; //주문상품 벤더
    private String purchaseProductOption;//주문상품 옵션
    private Integer purchaseProductCount;// 주문상품수량
    private Integer purchaseProductPrice; //주문상품금액
    private Integer purchaseProductDiscountPrice; //주문상품할인액
    private String productThumbnail; //주문상품이미지
    private String deliveryType; //주문상품배송타입
    private String productState; //주문상품 주문배송상태
    //여기까지는 PurchaseProductDTO로


    public static PurchaseDto voToPurchaseDto
            (String purchaser, String purchaseUuid, String purchaserPhoneNum,
             String purchaseEmail, String recipient, String recipientPhoneNum,
             String recipientEmail, String address, String purchaseRoadAddress,
             String purchaseJibunAddress, String purchaseDetailAddress,
             String deliverymessage, Long shippingFee) {

        return PurchaseDto.builder()
                .purchaser(purchaser)
                .purchaseUuid(purchaseUuid)
                .purchaserPhoneNum(purchaserPhoneNum)
                .purchaseEmail(purchaseEmail)
                .recipient(recipient)
                .recipientPhoneNum(recipientPhoneNum)
                .recipientEmail(recipientEmail)
                .address(address)
                .purchaseRoadAddress(purchaseRoadAddress)
                .purchaseJibunAddress(purchaseJibunAddress)
                .purchaseDetailAddress(purchaseDetailAddress)
                .deliverymessage(deliverymessage)
                .shippingFee(shippingFee)
                .build();

    }

    public static PurchaseProductDto purchaseProductDto
            (Long purchaseProductSeq,Long productId, String purchaseProductName,String purchaseProductVendor,
             String purchaseProductOption,Integer purchaseProductCount,Integer purchaseProductPrice,
             Integer purchaseProductDiscountPrice,String productThumbnail,String deliveryType,
             String productState){

        return PurchaseProductDto.builder()
                .purchaseProductSeq(purchaseProductSeq)
                .productId(productId)
                .purchaseProductName(purchaseProductName)
                .purchaseProductVendor(purchaseProductVendor)
                .purchaseProductOption(purchaseProductOption)
                .purchaseProductCount(purchaseProductCount)
                .purchaseProductPrice(purchaseProductPrice)
                .purchaseProductDiscountPrice(purchaseProductDiscountPrice)
                .productThumbnail(productThumbnail)
                .deliveryType(DeliveryType.valueOf(deliveryType))
                .productState(productState)
                .build();

    }

}
