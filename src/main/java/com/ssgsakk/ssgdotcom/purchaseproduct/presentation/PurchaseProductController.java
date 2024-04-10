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


    @PutMapping("/update/purchaseProductState/{purchaseProductSeq}")
    public void updatePurchaseProducState(@PathVariable Long purchaseProductSeq
                                           , @RequestBody PurchaseProductStateRequestVo purchaseProductStateRequestVo) {

        PurchaseProductStateDto purchaseProductStateDto = PurchaseProductStateDto.builder()
                .purchaseProductState(purchaseProductStateRequestVo.getPurchaseProductState())
                .build();

        purchaseProductService.updatePurchaseProductState(purchaseProductSeq, purchaseProductStateDto);


    }


}
