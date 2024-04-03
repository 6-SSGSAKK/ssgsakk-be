package com.ssgsakk.ssgdotcom.shippingAddress.application.impl;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.shippingAddress.application.ShippingAddressService;
import com.ssgsakk.ssgdotcom.shippingAddress.domain.ShippingAddress;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.*;
import com.ssgsakk.ssgdotcom.shippingAddress.infrastructure.ShippingAddressRepository;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindDetailShippingAddressInfoResponseVo;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindShippingAddressSeqsResponseVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    @Transactional
    public void changeDefaultAddress(ChangeDefaultAddressDto changeDefaultAddressDto) {
        // 기존 기본 shippinAddressSeq만 0으로 변경하고 전달받은 shippingAddressSeq만 1로 바꾸고 나머지는 전부 0으로 변환
        if (shippingAddressRepository.setDefaultAddress(changeDefaultAddressDto.getUuid()) == 0 || shippingAddressRepository.changeDefaultAddress(changeDefaultAddressDto.getShippingAddressSeq()) == 0) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public FindShippingAddressSeqsResponseVo findShippingAddressSeqs(GetShippingAddressListDto getShippingAddressListDto) {
        return FindShippingAddressSeqsResponseVo.builder()
                .shippingAddressList(shippingAddressRepository.findShippingAddressSeqs(getShippingAddressListDto.getUuid()))
                .build();
    }

    @Override
    @Transactional
    public FindDetailShippingAddressInfoResponseVo findDetailShippingAddressInfo(FindDetailShippingAddressInfoDto findDetailShippingAddressInfoDto) {
        return FindDetailShippingAddressInfoResponseVo.builder()
                .shippingAddressList(shippingAddressRepository.findByUuid(findDetailShippingAddressInfoDto.getUuid()))
                .build();
    }

    @Override
    @Transactional
    public void addShippingAddress(AddShippingAddressDto addShippingAddressDto) {
        // 전달받은 DTO로 shippingAddress 엔티티 만들어서 저장
        shippingAddressRepository.save(ShippingAddress.builder()
                .uuid(addShippingAddressDto.getUuid())
                .addressNickname(addShippingAddressDto.getAddressNickname())
                .receiverName(addShippingAddressDto.getReceiverName())
                .receiverMobileNum(addShippingAddressDto.getReceiverMobileNum())
                .zipCode(addShippingAddressDto.getZipCode())
                .roadAddress(addShippingAddressDto.getRoadAddress())
                .jibunAddress(addShippingAddressDto.getJibunAddress())
                .detailAddress(addShippingAddressDto.getDetailAddress())
                .build());
    }

    @Override
    @Transactional
    public void changeShippingAddress(ChangeShippingAddressDto changeShippingAddressDto) {
        shippingAddressRepository.changeShippingAddress(
                changeShippingAddressDto.getShippingAddressSeq()
                , changeShippingAddressDto.getAddressNickname()
                , changeShippingAddressDto.getReceiverName()
                , changeShippingAddressDto.getReceiverMobileNum()
                , changeShippingAddressDto.getZipCode()
                , changeShippingAddressDto.getRoadAddress()
                , changeShippingAddressDto.getJibunAddress()
                , changeShippingAddressDto.getDetailAddress()
        );
    }

    @Override
    @Transactional
    public void deleteShippingAddress(DeleteShippingAddressDto deleteShippingAddressDto) {
        shippingAddressRepository.deleteByShippingAddressSeq(deleteShippingAddressDto.getShippingAddressSeq());
    }
}
