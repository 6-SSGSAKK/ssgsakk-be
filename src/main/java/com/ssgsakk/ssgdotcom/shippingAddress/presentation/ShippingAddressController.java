package com.ssgsakk.ssgdotcom.shippingAddress.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import com.ssgsakk.ssgdotcom.shippingAddress.application.ShippingAddressService;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.ChangeDefaultAddressDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/shipping-addr")
@RequiredArgsConstructor
public class ShippingAddressController {
    private final JWTUtil jwtUtil;
    private final ShippingAddressService shippingAddressService;

    @Operation(summary = "기본 배송지 변경", description = "기본 배송지 변경", tags = {"Default Address Change"})
    @PatchMapping("/{shippingAddressSeq}/default")
    public BaseResponse<Object> changeDefaultAddress(@RequestHeader("Authorization") String accessToken, @PathVariable("shippingAddressSeq") Long shippingAddressSeq) {
        String uuid = getUuid(accessToken);

        // shippingAddressSeq를 1로 만들고 나머지는 0으로 변경
        shippingAddressService.changeDefaultAddress(ChangeDefaultAddressDto.builder()
                .uuid(uuid)
                .shippingAddressSeq(shippingAddressSeq)
                .build());

        return new BaseResponse<>("기본 배송지 변경", null);
    }


    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
