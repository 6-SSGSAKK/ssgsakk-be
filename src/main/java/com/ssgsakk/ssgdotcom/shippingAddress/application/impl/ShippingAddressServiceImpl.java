package com.ssgsakk.ssgdotcom.shippingAddress.application.impl;

import com.ssgsakk.ssgdotcom.shippingAddress.application.ShippingAddressService;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;
import com.ssgsakk.ssgdotcom.shippingAddress.infrastructure.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto) {
        shippingAddressRepository.changeDefaultAddress(changeDefaultAddressDto.getUuid(), changeDefaultAddressDto.getShippingAddressSeq());
    }
}
