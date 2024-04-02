package com.ssgsakk.ssgdotcom.shippingAddress.application.impl;

import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.shippingAddress.application.ShippingAddressService;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;
import com.ssgsakk.ssgdotcom.shippingAddress.infrastructure.ShippingAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto) {
        // 기존 기본 shippinAddressSeq만 0으로 변경하고 전달받은 shippingAddressSeq만 1로 바꾸고 나머지는 전부 0으로 변환
        User user = memberRepository.findByUuid(changeDefaultAddressDto.getUuid()).get();
        shippingAddressRepository.setDefaultAddress(user);
        shippingAddressRepository.changeDefaultAddress(changeDefaultAddressDto.getShippingAddressSeq());
    }
}
