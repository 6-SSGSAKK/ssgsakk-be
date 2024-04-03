package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteShippingAddressDto {
    private Long shippingAddressSeq;

    @Builder
    public DeleteShippingAddressDto(Long shippingAddressSeq) {
        this.shippingAddressSeq = shippingAddressSeq;
    }
}
