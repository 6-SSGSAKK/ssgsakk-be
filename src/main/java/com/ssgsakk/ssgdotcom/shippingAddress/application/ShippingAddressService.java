package com.ssgsakk.ssgdotcom.shippingAddress.application;

import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;

public interface ShippingAddressService {
    void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto);
}
