package com.ssgsakk.ssgdotcom.shippingAddress.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindShippingAddressSeqsResponseVo {
    private List<Integer> shippingAddressList;

    @Builder
    public FindShippingAddressSeqsResponseVo(List<Integer> shippingAddressList) {
        this.shippingAddressList = shippingAddressList;
    }
}
