package com.ssgsakk.ssgdotcom.purchase.domain;
import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Purchase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq; //주문ID

    @Column(length = 50)
    private String purchaser; //주문자이름

//    @Column(columnDefinition = "text")
    @Column(length = 50)
    private String purchaseCode; //주문코드

    @Column(length = 50)
    private String purchaseuuid; //UUID

    @Column(length = 50)
    private String purchaserPhoneNum; //주문자 전화번호

    @Column(length = 50)
    private String purchaseEmail; //주문자 이메일

    @Column(length = 50)
    private String recipient; //수령자 이름

    @Column(length = 50)
    private String recipientPhoneNum; //수령자 전화번호

    @Column(length = 50)
    private String recipientEmail; //수령자 이메일

    @Column(length = 50)
    private String purchaseZipcode; //최종배송지우편번호

    @Column(length = 50)
    private String purchaseRoadAddress; //최종배송지 도로명주소

    @Column(length = 50)
    private String purchaseJibunAddress; //최종배송지지번주소

    @Column(length = 50)
    private String purchaseDetailAddress; //최종배송지상세주소

    @Column(length = 100)
    private String deliverymessage; //배송메시지

    @Column(length = 20)
    private Long shippingFee; //배송비

    @Builder
    public Purchase(Long purchaseSeq, String purchaser, String purchaseCode, String purchaseuuid,
                    String purchaserPhoneNum, String purchaseEmail, String recipient,
                    String recipientPhoneNum, String recipientEmail, String purchaseZipcode,
                    String purchaseRoadAddress, String purchaseJibunAddress, String purchaseDetailAddress,
                    String deliverymessage, Long shippingFee) {

        this.purchaseSeq = purchaseSeq;
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
