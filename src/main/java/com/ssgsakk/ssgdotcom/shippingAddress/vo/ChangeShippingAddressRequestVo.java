package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeShippingAddressRequestVo {
    private String addressNickname;
    private String receiverName;
    private String receiverMobileNum;
    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;

    @Builder
    public ChangeShippingAddressRequestVo(String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress) {
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.receiverMobileNum = receiverMobileNum;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
    }
}
