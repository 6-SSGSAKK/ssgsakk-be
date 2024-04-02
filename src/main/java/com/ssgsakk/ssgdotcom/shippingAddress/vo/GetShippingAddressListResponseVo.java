package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetShippingAddressListResponseVo {
    private List<Integer> shippingAddressList;

    @Builder
    public GetShippingAddressListResponseVo(List<Integer> shippingAddressList) {
        this.shippingAddressList = shippingAddressList;
    }
}
