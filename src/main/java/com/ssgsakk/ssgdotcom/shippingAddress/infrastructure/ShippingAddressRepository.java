package com.ssgsakk.ssgdotcom.shippingAddress.infrastructure;

import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
