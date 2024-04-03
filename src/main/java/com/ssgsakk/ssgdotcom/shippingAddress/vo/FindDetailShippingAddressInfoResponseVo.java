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
    private ShippingAddress shippingAddress;

    @Builder
    public FindDetailShippingAddressInfoResponseVo(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
