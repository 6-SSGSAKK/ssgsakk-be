package com.ssgsakk.ssgdotcom.shippingAddress.infrastructure;

import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User u SET userPassword = :password WHERE u.uuid = :uuid")
    void changeDefaultAddress(String uuid, Long shippingAddressSeq);
}
