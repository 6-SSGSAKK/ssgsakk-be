package com.ssgsakk.ssgdotcom.shippingAddress.infrastructure;

import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    // 기존 기본 배송지 value를 0으로 변환
    @Modifying
    @Query("UPDATE ShippingAddress sa SET sa.defaultAddressCheck = 0 WHERE sa.uuid = :uuid")
    int setDefaultAddress(@Param("uuid") String uuid);

    // 지정받은 배송지 seq를 1로 변환
    @Modifying
    @Query("UPDATE ShippingAddress sa SET sa.defaultAddressCheck = 1 WHERE sa.shippingAddressSeq = :shippingAddressSeq")
    int changeDefaultAddress(@Param("shippingAddressSeq") Long shippingAddressSeq);

    @Query("SELECT sa.shippingAddressSeq FROM ShippingAddress sa WHERE sa.uuid = :uuid")
    List<Integer> findShippingAddressSeqs(@Param("uuid") String uuid);

//    List<ShippingAddress> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE ShippingAddress sa SET sa.addressNickname = :addressNickname, sa.receiverName = :receiverName, sa.receiverMobileNum = :receiverMobileNum, sa.zipCode = :zipCode, sa.roadAddress = :roadAddress, sa.jibunAddress = :jibunAddress, sa.detailAddress = :detailAddress WHERE sa.shippingAddressSeq = :shippingAddressSeq")
    void changeShippingAddress(Long shippingAddressSeq, String addressNickname, String receiverName, String receiverMobileNum, String zipCode, String roadAddress, String jibunAddress, String detailAddress);

    void deleteByShippingAddressSeq(Long shippingAddressSeq);

    ShippingAddress findByShippingAddressSeq(Long shippingAddressSeq);
}
