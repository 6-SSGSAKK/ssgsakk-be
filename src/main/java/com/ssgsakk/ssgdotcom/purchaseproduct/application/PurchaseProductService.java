package com.ssgsakk.ssgdotcom.purchaseproduct.application;

import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseProductService {

    void savePurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase);

    @Transactional
    void savedNonMemberPurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase);
}
