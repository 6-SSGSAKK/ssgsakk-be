package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeShippingAddressDto {
    private Long shippingAddressSeq;
    private String addressNickname;
    private String receiverName;
    private String receiverMobileNum;
    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;

    @Builder
    public ChangeShippingAddressDto(Long shippingAddressSeq, String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress) {
        this.shippingAddressSeq = shippingAddressSeq;
        this.addressNickname = addressNickname;
        this.receiverName = receiverName;
        this.receiverMobileNum = receiverMobileNum;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
    }
}
