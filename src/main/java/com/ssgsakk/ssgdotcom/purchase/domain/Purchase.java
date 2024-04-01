package com.ssgsakk.ssgdotcom.purchase.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseSeq;

    @Column(length = 50)
    private String purchaser; //주문자이름

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
    private String finalAddress; //최종배송지우편번호

    @Column(length = 50)
    private String finalRoadAddress; //최종배송지 도로명주소

    @Column(length = 50)
    private String finalJibunAddress; //최종배송지지번주소

    @Column(length = 50)
    private String finalDetailAddress; //최종배송지상세주소

    @Column(length = 100)
    private String deliverymessage; //배송메시지

    @Builder
    public Purchase(Long purchaseSeq, String purchaser, String purchaserPhoneNum, String purchaseEmail,
                    String recipient, String recipientPhoneNum, String recipientEmail, String finalAddress,
                    String finalRoadAddress, String finalJibunAddress, String finalDetailAddress, String deliverymessage) {

        this.purchaseSeq = purchaseSeq;
        this.purchaser = purchaser;
        this.purchaserPhoneNum = purchaserPhoneNum;
        this.purchaseEmail = purchaseEmail;
        this.recipient = recipient;
        this.recipientPhoneNum = recipientPhoneNum;
        this.recipientEmail = recipientEmail;
        this.finalAddress = finalAddress;
        this.finalRoadAddress = finalRoadAddress;
        this.finalJibunAddress = finalJibunAddress;
        this.finalDetailAddress = finalDetailAddress;
        this.deliverymessage = deliverymessage;

    }
}
