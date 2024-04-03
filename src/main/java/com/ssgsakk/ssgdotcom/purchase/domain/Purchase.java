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
    private String address; //최종배송지우편번호

    @Column(length = 50)
    private String roadAddress; //최종배송지 도로명주소

    @Column(length = 50)
    private String jibunAddress; //최종배송지지번주소

    @Column(length = 50)
    private String detailAddress; //최종배송지상세주소

    @Column(length = 100)
    private String deliverymessage; //배송메시지

    @Column(columnDefinition = "boolean default false")
    private Boolean cancelltionStatus; //취소여부



    @Builder
    public Purchase(Long purchaseSeq, String purchaser,
                    String purchaserPhoneNum, String purchaseEmail, String recipient,
                    String recipientPhoneNum, String recipientEmail, String address, String roadAddress,
                    String jibunAddress, String detailAddress, String deliverymessage, Boolean cancelltionStatus) {

        this.purchaseSeq = purchaseSeq;
        this.purchaser = purchaser;
        this.purchaserPhoneNum = purchaserPhoneNum;
        this.purchaseEmail = purchaseEmail;
        this.recipient = recipient;
        this.recipientPhoneNum = recipientPhoneNum;
        this.recipientEmail = recipientEmail;
        this.address = address;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.deliverymessage = deliverymessage;
        this.cancelltionStatus = cancelltionStatus;

    }
}
