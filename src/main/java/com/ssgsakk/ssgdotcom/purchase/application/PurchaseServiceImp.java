package com.ssgsakk.ssgdotcom.purchase.application;

import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.infrastructure.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Override
    public void createpurchase(PurchaseDto purchaseDto){

        Purchase purchase = Purchase.builder()
                .purchaser((purchaseDto.getPurchaser()))
                .purchaseEmail(purchaseDto.getPurchaseEmail())
                .purchaseEmail(purchaseDto.getPurchaseEmail())
                .recipient(purchaseDto.getRecipient())
                .recipientPhoneNum(purchaseDto.getRecipientPhoneNum())
                .recipientEmail(purchaseDto.getRecipientEmail())
                .finalAddress(purchaseDto.getFinalAddress())
                .finalRoadAddress(purchaseDto.getFinalRoadAddress())
                .finalJibunAddress(purchaseDto.getFinalJibunAddress())
                .finalDetailAddress(purchaseDto.getFinalDetailAddress())
                .deliverymessage(purchaseDto.getDeliverymessage())
                .build();

        purchaseRepository.save(purchase);

    }



}