package com.ssgsakk.ssgdotcom.shippingAddress.application;

import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.GetShippingAddressListDto;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.GetShippingAddressListResponseVo;

public interface ShippingAddressService {
    void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto);

    GetShippingAddressListResponseVo findShippingAddressSeqs(GetShippingAddressListDto getShippingAddressListDto);
}
