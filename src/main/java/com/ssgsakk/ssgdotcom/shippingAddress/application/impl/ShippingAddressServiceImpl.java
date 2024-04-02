package com.ssgsakk.ssgdotcom.shippingAddress.application.impl;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
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
        if (shippingAddressRepository.setDefaultAddress(changeDefaultAddressDto.getUuid()) == 0 || shippingAddressRepository.changeDefaultAddress(changeDefaultAddressDto.getShippingAddressSeq()) == 0) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
