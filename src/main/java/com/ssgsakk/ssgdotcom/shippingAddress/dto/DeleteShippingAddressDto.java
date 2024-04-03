package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteShippingAddressDto {
    private Long shippingAddressSeq;

    @Builder
    public DeleteShippingAddressDto(Long shippingAddressSeq) {
        this.shippingAddressSeq = shippingAddressSeq;
    }
}
