package com.ssgsakk.ssgdotcom.purchase.application;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.dto.UpdateCancelltionDto;
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
                .purchaserPhoneNum(purchaseDto.getPurchaserPhoneNum())
                .recipient(purchaseDto.getRecipient())
                .recipientPhoneNum(purchaseDto.getRecipientPhoneNum())
                .recipientEmail(purchaseDto.getRecipientEmail())
                .address(purchaseDto.getAddress())
                .roadAddress(purchaseDto.getRoadAddress())
                .jibunAddress(purchaseDto.getJibunAddress())
                .detailAddress(purchaseDto.getDetailAddress())
                .deliverymessage(purchaseDto.getDeliverymessage())
                .cancelltionStatus(purchaseDto.getCancelltionStatus())
                .build();

        purchaseRepository.save(purchase);

    }

    @Override
    public void updateCancelltion(UpdateCancelltionDto updateCancelltionDto){

        Purchase purchase = purchaseRepository.findById(updateCancelltionDto.getPurchaseSeq())
                .orElseThrow(()-> new BusinessException(ErrorCode.CANNOT_FOUND_PURCHASE));

        Purchase updateCancelltion = Purchase.builder()
                .purchaseSeq(updateCancelltionDto.getPurchaseSeq())
                .purchaser(purchase.getPurchaser())
                .purchaserPhoneNum(purchase.getPurchaserPhoneNum())
                .purchaseEmail(purchase.getPurchaseEmail())
                .recipient(purchase.getRecipient())
                .recipientPhoneNum(purchase.getRecipientPhoneNum())
                .recipientEmail(purchase.getRecipientEmail())
                .address(purchase.getAddress())
                .roadAddress(purchase.getRoadAddress())
                .jibunAddress(purchase.getJibunAddress())
                .detailAddress(purchase.getDetailAddress())
                .deliverymessage(purchase.getDeliverymessage())
                .cancelltionStatus(updateCancelltionDto.getCancelltionStatus())
                .build();

        purchaseRepository.save(updateCancelltion);
    }








}