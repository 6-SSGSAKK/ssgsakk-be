package com.ssgsakk.ssgdotcom.shippingAddress.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import com.ssgsakk.ssgdotcom.shippingAddress.application.ShippingAddressService;
import com.ssgsakk.ssgdotcom.shippingAddress.dto.*;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.AddShippingAddressRequestVo;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.ChangeShippingAddressRequestVo;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindDetailShippingAddressInfoResponseVo;
import com.ssgsakk.ssgdotcom.shippingAddress.vo.FindShippingAddressSeqsResponseVo;
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

    @Operation(summary = "배송지 목록 조회", description = "배송지 목록 조회", tags = {"Find Shipping Address Seqs"})
    @GetMapping("/list")
    public BaseResponse<FindShippingAddressSeqsResponseVo> findShippingAddressSeqs(@RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);

        FindShippingAddressSeqsResponseVo findShippingAddressSeqsResponseVo = shippingAddressService.findShippingAddressSeqs(GetShippingAddressListDto.builder()
                .uuid(uuid)
                .build());

        return new BaseResponse<>("배송지 목록 조회", findShippingAddressSeqsResponseVo);
    }

    @Operation(summary = "상세 배송지 조회", description = "상세 배송지 조회", tags = {"Find Detail Shipping Address Information"})
    @GetMapping("/{shippingAddressSeq}")
    public BaseResponse<FindDetailShippingAddressInfoResponseVo> findDetailShippingAddressInfo(@RequestHeader("Authorization") String accessToken
            , @PathVariable("shippingAddressSeq") Long shippingAddressSeq) {
        String uuid = getUuid(accessToken);

//        FindDetailShippingAddressInfoResponseVo findDetailShippingAddressInfoResponseVo = shippingAddressService.findDetailShippingAddressInfo(FindDetailShippingAddressInfoDto.builder()
//                .shippingAddressSeq(shippingAddressSeq)
//                .build());

        return new BaseResponse<>("배송지 상세 정보 조회", shippingAddressService.findDetailShippingAddressInfo(
                FindDetailShippingAddressInfoDto.builder()
                        .shippingAddressSeq(shippingAddressSeq)
                        .build()
        ));
    }

    @Operation(summary = "배송지 추가", description = "배송지 추가", tags = {"Add Shipping Address"})
    @PostMapping("")
    public BaseResponse<Object> addShippingAddress(@RequestHeader("Authorization") String accessToken, @RequestBody AddShippingAddressRequestVo addShippingAddressRequestVo) {
        String uuid = getUuid(accessToken);

        shippingAddressService.addShippingAddress(AddShippingAddressDto.builder()
                .uuid(uuid)
                .addressNickname(addShippingAddressRequestVo.getAddressNickname())
                .receiverName(addShippingAddressRequestVo.getReceiverName())
                .receiverMobileNum(addShippingAddressRequestVo.getReciverMobileNum())
                .zipCode(addShippingAddressRequestVo.getZipCode())
                .roadAddress(addShippingAddressRequestVo.getRoadAddress())
                .jibunAddress(addShippingAddressRequestVo.getJibunAddress())
                .detailAddress(addShippingAddressRequestVo.getDetailAddress())
                .build());

        return new BaseResponse<>("배송지 추가", null);
    }

    @Operation(summary = "배송지 수정", description = "배송지 수정", tags = {"Change Shipping Address"})
    @PutMapping("{shippingAddressSeq}")
    public BaseResponse<Object> changeShippingAddress(@RequestHeader("Authorization") String accessToken, @PathVariable("shippingAddressSeq") Long shippingAddressSeq, @RequestBody ChangeShippingAddressRequestVo changeShippingAddressRequestVo) {
        String uuid = getUuid(accessToken);

        shippingAddressService.changeShippingAddress(ChangeShippingAddressDto.builder()
                .shippingAddressSeq(shippingAddressSeq)
                .addressNickname(changeShippingAddressRequestVo.getAddressNickname())
                .receiverName(changeShippingAddressRequestVo.getReceiverName())
                .receiverMobileNum(changeShippingAddressRequestVo.getReceiverMobileNum())
                .zipCode(changeShippingAddressRequestVo.getZipCode())
                .roadAddress(changeShippingAddressRequestVo.getRoadAddress())
                .jibunAddress(changeShippingAddressRequestVo.getJibunAddress())
                .detailAddress(changeShippingAddressRequestVo.getDetailAddress())
                .build());

        return new BaseResponse<>("배송지 수정", null);
    }

    @Operation(summary = "배송지 삭제", description = "배송지 삭제", tags = {"Delete Shipping Address"})
    @DeleteMapping("{shippingAddressSeq}")
    public BaseResponse<Object> deleteShippingAddress(@RequestHeader("Authorization") String accessToken, @PathVariable("shippingAddressSeq") Long shippingAddressSeq) {
        String uuid = getUuid(accessToken);

        shippingAddressService.deleteShippingAddress(DeleteShippingAddressDto.builder()
                .shippingAddressSeq(shippingAddressSeq)
                .build()
        );

        return new BaseResponse<>("배송지 삭제", null);
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
