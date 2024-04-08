package com.ssgsakk.ssgdotcom.purchase.presentation;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseCodeDto;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateMemberPurchaseRequestVo;
import com.ssgsakk.ssgdotcom.purchase.vo.PurchaseCodeResponseVo;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final JWTUtil jwtUtil;
    private final PurchaseService purchaseService;


    @PostMapping("/member-purchase")
    public BaseResponse<PurchaseCodeResponseVo> createMemberPurchase(@RequestBody CreateMemberPurchaseRequestVo createMemberPurchaseRequestVo,
                                             @RequestHeader("Authorization") String accessToken ) {
        String uuid = getUuid(accessToken);
        PurchaseCodeDto purchaseCodeDto = purchaseService.createMemberPurchase(CreateMemberPurchaseRequestVo.voToPurchaseDto(createMemberPurchaseRequestVo),
                CreateMemberPurchaseRequestVo
                        .voListToPurchaseProductDtoList(createMemberPurchaseRequestVo.getPurchaseProductDtoList()));
        return new BaseResponse<>("주문ID", PurchaseCodeResponseVo.builder()
                .purchaseCode(purchaseCodeDto.getPurchaseCode())
                .build());
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