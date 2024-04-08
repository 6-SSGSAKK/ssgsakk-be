package com.ssgsakk.ssgdotcom.purchase.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Getter
@NoArgsConstructor
public class PurchaseDto {


    private String purchaser; //주문자이름
    private String purchaseCode; //주문코드
    private String purchaseuuid; //UUID
    private String purchaserPhoneNum; //주문자 전화번호
    private String purchaseEmail; //주문자 이메일
    private String recipient; //수령자 이름
    private String recipientPhoneNum; //수령자 전화번호
    private String recipientEmail; //수령자 이메일
    private String purchaseZipcode; //최종배송지우편번호
    private String purchaseRoadAddress; //최종배송지 도로명주소
    private String purchaseJibunAddress; //최종배송지지번주소
    private String purchaseDetailAddress; //최종배송지상세주소
    private String deliverymessage; //배송메시지
    private Long shippingFee; //배송비


    @Builder
    public PurchaseDto( String purchaser, String purchaseCode, String purchaseuuid,
                       String purchaserPhoneNum, String purchaseEmail, String recipient,
                       String recipientPhoneNum, String recipientEmail, String purchaseZipcode,
                       String purchaseRoadAddress, String purchaseJibunAddress, String purchaseDetailAddress,
                       String deliverymessage, Long shippingFee) {


        this.purchaser = purchaser;
        this.purchaseCode = purchaseCode;
        this.purchaseuuid = purchaseuuid;
        this.purchaserPhoneNum = purchaserPhoneNum;
        this.purchaseEmail = purchaseEmail;
        this.recipient = recipient;
        this.recipientPhoneNum = recipientPhoneNum;
        this.recipientEmail = recipientEmail;
        this.purchaseZipcode = purchaseZipcode;
        this.purchaseRoadAddress = purchaseRoadAddress;
        this.purchaseJibunAddress = purchaseJibunAddress;
        this.purchaseDetailAddress = purchaseDetailAddress;
        this.deliverymessage = deliverymessage;
        this.shippingFee = shippingFee;

    }


}
