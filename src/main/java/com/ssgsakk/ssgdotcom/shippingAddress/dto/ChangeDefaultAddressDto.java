package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class ChangeDefaultAddressDto {
    private String uuid;
    private Long shippingAddressSeq;

    @Builder
    public ChangeDefaultAddressDto(String uuid, Long shippingAddressSeq) {
        this.uuid = uuid;
        this.shippingAddressSeq = shippingAddressSeq;
    }
}
