package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindDetailShippingAddressInfoResponseVo {
    private List<ShippingAddress> shippingAddressList;

    @Builder
    public FindDetailShippingAddressInfoResponseVo(List<ShippingAddress> shippingAddressList) {
        this.shippingAddressList = shippingAddressList;
    }
}
