package com.ssgsakk.ssgdotcom.purchase.presentation;
import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.dto.UpdateCancelltionDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreatePurchaseRequestVo;
import com.ssgsakk.ssgdotcom.purchase.vo.UpdateCancelltionStatusRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;


    @PostMapping      //주문생성
    public void createorder(@RequestBody CreatePurchaseRequestVo createPurchaseRequestVo){


        purchaseService.createpurchase(PurchaseDto.builder()
                .purchaser(createPurchaseRequestVo.getPurchaser())
                .purchaseEmail(createPurchaseRequestVo.getPurchaseEmail())
                .purchaserPhoneNum(createPurchaseRequestVo.getPurchaserPhoneNum())
                .recipient(createPurchaseRequestVo.getRecipient())
                .recipientEmail(createPurchaseRequestVo.getRecipientEmail())
                .recipientPhoneNum(createPurchaseRequestVo.getRecipientPhoneNum())
                .address(createPurchaseRequestVo.getAddress())
                .roadAddress(createPurchaseRequestVo.getRoadAddress())
                .jibunAddress(createPurchaseRequestVo.getJibunAddress())
                .detailAddress(createPurchaseRequestVo.getDetailAddress())
                .deliverymessage(createPurchaseRequestVo.getDeliverymessage())
                .cancelltionStatus(createPurchaseRequestVo.getCancelltionStatus())
                .build());
    }
    @PutMapping("/update/cacelltionstatus/{purchaseSeq}") //취소여부 수정
    public void updateCancelltion(@PathVariable Long purchaseSeq,
                                  @RequestBody UpdateCancelltionStatusRequestVo updateCancelltionStatusRequestVo){
        purchaseService.updateCancelltion(UpdateCancelltionDto.builder()
                .purchaseSeq(purchaseSeq)
                .cancelltionStatus(updateCancelltionStatusRequestVo.getCancelltionStatus())
                .build());

    }

}
