package com.ssgsakk.ssgdotcom.order.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Entity
@NoArgsConstructor
public class Order {

    @Id
    private Long orderSeq;

    private Timestamp orderAt;

    private String orderer; //주문자이름

    private String ordererPhoneNum; //주문자 전화번호

    private String ordererEmail; //주문자 이메일

    private String recipient; //수령자 이름

    private String recipientPhoneNum; //수령자 전화번호

    private String recipientEmail; //수령자 이메일

    private String finalAsdress; //최종배송지우편번호

    private String finalRoadAddress; //최종배송지 도로명주소

    private String finalJibunAddress; //최종배송지지번주소

    private String finalDetailAddress; //최종배송지상세주소

    private String deliverymessage; //배송메시지




}
