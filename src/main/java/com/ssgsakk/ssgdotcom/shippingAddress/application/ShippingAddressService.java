package com.ssgsakk.ssgdotcom.shippingAddress.application;

import com.ssgsakk.ssgdotcom.shippingAddress.dto.AddShippingAddressDto;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.FindDetailShippingAddressInfoDto;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.GetShippingAddressListDto;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindDetailShippingAddressInfoResponseVo;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindShippingAddressSeqsResponseVo;

public interface ShippingAddressService {
    void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto);

    FindShippingAddressSeqsResponseVo findShippingAddressSeqs(GetShippingAddressListDto getShippingAddressListDto);

    FindDetailShippingAddressInfoResponseVo findDetailShippingAddressInfo(FindDetailShippingAddressInfoDto findDetailShippingAddressInfoDto);

    void addShippingAddress(AddShippingAddressDto addShippingAddressDto);
}
