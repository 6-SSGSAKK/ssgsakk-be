package com.ssgsakk.ssgdotcom.shippingAddress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetShippingAddressListDto {
    private String uuid;
    private List<Integer> shippingAddressList;

    @Builder
    public GetShippingAddressListDto(String uuid, List<Integer> shippingAddressList) {
        this.uuid = uuid;
        this.shippingAddressList = shippingAddressList == null ? new ArrayList<>() : shippingAddressList;
    }
}
