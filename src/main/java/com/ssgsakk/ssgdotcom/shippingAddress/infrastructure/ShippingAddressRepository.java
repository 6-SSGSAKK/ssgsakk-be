package com.ssgsakk.ssgdotcom.shippingAddress.infrastructure;

import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    // 기존 기본 배송지 value를 0으로 변환
    @Transactional
    @Modifying
    @Query("UPDATE ShippingAddress sa SET sa.defaultAddressCheck = 0 WHERE sa.user = :user")
    void setDefaultAddress(@Param("user") User user);

    // 지정받은 배송지 seq를 1로 변환
    @Transactional
    @Modifying
    @Query("UPDATE ShippingAddress sa SET sa.defaultAddressCheck = 1 WHERE sa.shippingAddressSeq = :shippingAddressSeq")
    void changeDefaultAddress(@Param("shippingAddressSeq") Long shippingAddressSeq);

}
