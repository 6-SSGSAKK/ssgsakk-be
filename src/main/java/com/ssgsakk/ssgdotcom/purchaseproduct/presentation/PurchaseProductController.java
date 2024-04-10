package com.ssgsakk.ssgdotcom.purchaseproduct.presentation;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.purchaseproduct.Vo.PurchaseProductStateRequestVo;
import com.ssgsakk.ssgdotcom.purchaseproduct.application.PurchaseProductService;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductStateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchaseproduct")
@RequiredArgsConstructor
public class PurchaseProductController {

    private final PurchaseProductService purchaseProductService;


    @PutMapping("/update/purchaseProductState/{purchaseProductSeq}") //주문상태 변경 Controller
    public void updatePurchaseProducState(@PathVariable Long purchaseProductSeq //PurchaseProduct의 PK받음
                                           , @RequestBody PurchaseProductStateRequestVo purchaseProductStateRequestVo) {

        PurchaseProductStateDto purchaseProductStateDto = PurchaseProductStateDto.builder()
                .purchaseProductState(purchaseProductStateRequestVo.getPurchaseProductState())
                .build();

        purchaseProductService.updatePurchaseProductState(purchaseProductSeq, purchaseProductStateDto);


    }


}
