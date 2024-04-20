package com.ssgsakk.ssgdotcom.purchaseproduct.presentation;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.purchaseproduct.Vo.PurchaseProductStateRequestVo;
import com.ssgsakk.ssgdotcom.purchaseproduct.application.PurchaseProductService;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductListDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductStateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchaseproduct")
@RequiredArgsConstructor
public class PurchaseProductController {

    private final PurchaseProductService purchaseProductService;


    @PutMapping("/update/purchaseProductState/{purchaseProductSeq}")
    public BaseResponse<?> updatePurchaseProducState(@PathVariable Long purchaseProductSeq
                                           , @RequestBody PurchaseProductStateRequestVo purchaseProductStateRequestVo) {

        PurchaseProductStateDto purchaseProductStateDto = PurchaseProductStateDto.builder()
                .purchaseProductState(purchaseProductStateRequestVo.getPurchaseProductState())
                .build();

        purchaseProductService.updatePurchaseProductState(purchaseProductSeq, purchaseProductStateDto);

        if (purchaseProductStateRequestVo.getPurchaseProductState() == 5) {
            return new BaseResponse<>("cancelSuccess", "");
        }else if (purchaseProductStateRequestVo.getPurchaseProductState() >8 ) {
            return new BaseResponse<>("updateFail", "상태변경 숫자 확인");
        }
        return new BaseResponse<>("updateSuccess", "");

    }

    @GetMapping("/member-purchase-detail/{purchaseSeq}") //회원 주문상품상세내역 조회
    public BaseResponse<List<PurchaseProductListDto>> memberPurchaseDetail(@PathVariable Long purchaseSeq) {

        List<PurchaseProductListDto> purchaseList = purchaseProductService.memberPurchaseProductDetail(purchaseSeq);

        if (purchaseList != null) {
            return new BaseResponse<>("주문상세내역 조회 성공", purchaseList);
        }else {
            return new BaseResponse<>("주문상세내역 조회 실패",null);
        }

    }



}


