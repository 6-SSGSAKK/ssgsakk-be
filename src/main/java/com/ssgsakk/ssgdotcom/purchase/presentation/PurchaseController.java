package com.ssgsakk.ssgdotcom.purchase.presentation;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseCodeDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateMemberPurchaseRequestVo;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateNonMemberPurchaseRequestVo;
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


    @PostMapping("/member-purchase") //회원 주문생성 VO로 PurchseDto, List<PurchaseProductDto> 받기
    public BaseResponse<PurchaseCodeResponseVo> createMemberPurchase(@RequestBody CreateMemberPurchaseRequestVo createMemberPurchaseRequestVo, //VO값 받기
                                             @RequestHeader("Authorization") String accessToken ) { //Header 로 Authorization 받고 UUID 생성
        String uuid = getUuid(accessToken); //uuid생성
        PurchaseCodeDto purchaseCodeDto = purchaseService.createMemberPurchase(CreateMemberPurchaseRequestVo.voToPurchaseDto(createMemberPurchaseRequestVo,uuid),
                CreateMemberPurchaseRequestVo.voListToPurchaseProductDtoList(createMemberPurchaseRequestVo.getPurchaseProductDtoList()));
                //CreatePurchseRequestVo.voTOPurchseDto에 Vo,uuid 전달
                //voListToPurchseProductDtoList에 Vo,PurchaseProductDtoList 전달

        return new BaseResponse<>("주문ID", PurchaseCodeResponseVo.builder()
                .purchaseCode(purchaseCodeDto.getPurchaseCode())
                .build()); //PurchaseCode에 담겨있는 purcaseCode를 DTO -> VO 로 Response
    }

    @PostMapping("non-member-purchase") //비회원 주문 생성
    public BaseResponse<PurchaseCodeResponseVo> createNonMemberPurchase(@RequestBody CreateNonMemberPurchaseRequestVo createNonMemberPurchaseRequestVo) {
        PurchaseCodeDto purchaseCodeDto = purchaseService.createNonMemberPurchase(CreateNonMemberPurchaseRequestVo.voToPurchaseDto(createNonMemberPurchaseRequestVo)
                , CreateNonMemberPurchaseRequestVo.voToPurchaseProductDtoList(createNonMemberPurchaseRequestVo.getPurchaseProductDtoList()));
        return new BaseResponse<>("주문ID", PurchaseCodeResponseVo.builder()
                .purchaseCode(purchaseCodeDto.getPurchaseCode())
                .build());
    }





}