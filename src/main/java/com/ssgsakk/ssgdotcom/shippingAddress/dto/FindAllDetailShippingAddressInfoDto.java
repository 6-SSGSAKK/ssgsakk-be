package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAllDetailShippingAddressInfoDto {
    private String uuid;

    @Builder
    public FindAllDetailShippingAddressInfoDto(String uuid) {
        this.uuid = uuid;
    }
}
