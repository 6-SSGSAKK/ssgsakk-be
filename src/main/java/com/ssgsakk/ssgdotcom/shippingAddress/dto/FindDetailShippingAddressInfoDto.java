package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindDetailShippingAddressInfoDto {
    private Long shippingAddressSeq;

    @Builder
    public FindDetailShippingAddressInfoDto(Long shippingAddressSeq) {
        this.shippingAddressSeq = shippingAddressSeq;
    }
}
