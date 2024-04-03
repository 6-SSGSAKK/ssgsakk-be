package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindDetailShippingAddressInfoDto {
    private String uuid;

    @Builder
    public FindDetailShippingAddressInfoDto(String uuid) {
        this.uuid = uuid;
    }
}
