package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAllDetailShippingAddressInfoResponseVo {
    private Long shippingAddressSeq;
    private String addressNickname;
    private String receiverName;
    private String receiverMobileNum;
    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private int defaultAddressCheck;

    @Builder
    public FindAllDetailShippingAddressInfoResponseVo(Long shippingAddressSeq, String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress, int defaultAddressCheck) {
        this.shippingAddressSeq = shippingAddressSeq;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.receiverMobileNum = receiverMobileNum;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.defaultAddressCheck = defaultAddressCheck;
    }
}
