package com.ssgsakk.ssgdotcom.purchaseproduct.application;

import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure.PurchaseProductRepository;

public interface PurchaseProductService {

    void createPurchaseProduct(PurchaseProductDto purchaseProductDto);
}
