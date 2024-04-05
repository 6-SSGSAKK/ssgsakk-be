package com.ssgsakk.ssgdotcom.purchase.application;

import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;

public interface PurchaseService {


    Purchase createMemberPurchase(PurchaseDto purchaseDto);
}