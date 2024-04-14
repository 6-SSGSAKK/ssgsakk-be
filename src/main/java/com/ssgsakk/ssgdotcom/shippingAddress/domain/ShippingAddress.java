package com.ssgsakk.ssgdotcom.shippingAddress.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.member.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class ShippingAddress extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shippingAddressSeq;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String addressNickname;
    @Column(nullable = false, length = 50)
    private String receiverName;
    @Column(nullable = false, length = 50)
    private String receiverMobileNum;
    @Column(nullable = false, length = 50)
    private String zipCode;
    @Column(nullable = false, length = 50)
    private String roadAddress;
    @Column(nullable = false, length = 50)
    private String jibunAddress;
    @Column(nullable = false, length = 50)
    private String detailAddress;
    @Column(nullable = false, length = 5)
    private int defaultAddressCheck;        // 1이 기본 배송지, 0은 기타 배송지


    @Builder
    public ShippingAddress(long shippingAddressSeq, String uuid, String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress, int defaultAddressCheck) {
        this.shippingAddressSeq = shippingAddressSeq;
        this.uuid = uuid;
        this.addressNickname = addressNickname == null ? " " : addressNickname;
        this.receiverName = receiverName == null ? " " : receiverName;
        this.receiverMobileNum = receiverMobileNum == null ? " " : receiverMobileNum;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.defaultAddressCheck = defaultAddressCheck;
    }
}
