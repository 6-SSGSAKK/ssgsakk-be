package com.ssgsakk.ssgdotcom.purchase.application;

import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.dto.UpdateCancelltionDto;

public interface PurchaseService {
    void createMemberPurchase(PurchaseDto purchaseDto);
}