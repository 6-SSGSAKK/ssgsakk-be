package com.ssgsakk.ssgdotcom.purchase.presentation;

import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreatePurchaseRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;


    @PostMapping
    public void createorder(@RequestBody CreatePurchaseRequestVo createPurchaseRequestVo){


        purchaseService.createpurchase(PurchaseDto.builder()
                .purchaser(createPurchaseRequestVo.getPurchaser())
                .purchaseEmail(createPurchaseRequestVo.getPurchaseEmail())
                .purchaserPhoneNum(createPurchaseRequestVo.getPurchaserPhoneNum())
                .recipient(createPurchaseRequestVo.getRecipient())
                .recipientEmail(createPurchaseRequestVo.getRecipientEmail())
                .recipientPhoneNum(createPurchaseRequestVo.getRecipientPhoneNum())
                .finalAddress(createPurchaseRequestVo.getFinalAddress())
                .finalRoadAddress(createPurchaseRequestVo.getFinalRoadAddress())
                .finalJibunAddress(createPurchaseRequestVo.getFinalJibunAddress())
                .finalDetailAddress(createPurchaseRequestVo.getFinalDetailAddress())
                .deliverymessage(createPurchaseRequestVo.getDeliverymessage())
                .build());
    }

}
