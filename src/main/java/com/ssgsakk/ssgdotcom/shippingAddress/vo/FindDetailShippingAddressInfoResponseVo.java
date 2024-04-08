package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindDetailShippingAddressInfoResponseVo {
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
    public FindDetailShippingAddressInfoResponseVo(Long shippingAddressSeq, String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress, int defaultAddressCheck) {
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
