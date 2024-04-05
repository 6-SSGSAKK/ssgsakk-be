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
    public void createMemberPurchase(PurchaseDto purchaseDto){
        Purchase purchase = Purchase.builder()
                .purchaser(purchaseDto.getPurchaser())
                .purchaseUuid(purchaseDto.getPurchaseUuid())
                .purchaserPhoneNum(purchaseDto.getPurchaserPhoneNum())
                .purchaseEmail(purchaseDto.getPurchaseEmail())
                .recipient(purchaseDto.getRecipient())
                .recipientPhoneNum(purchaseDto.getRecipientPhoneNum())
                .recipientEmail(purchaseDto.getRecipientEmail())
                .purchaseZipcode(purchaseDto.getPurchaseZipcode())
                .purchaseRoadAddress(purchaseDto.getPurchaseRoadAddress())
                .purchaseJibunAddress(purchaseDto.getPurchaseJibunAddress())
                .purchaseDetailAddress(purchaseDto.getPurchaseDetailAddress())
                .deliverymessage(purchaseDto.getDeliverymessage())
                .shippingFee(purchaseDto.getShippingFee())
                .build();

        purchaseRepository.save(purchase);
    }




}