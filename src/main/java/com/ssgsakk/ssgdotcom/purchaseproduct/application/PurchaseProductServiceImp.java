package com.ssgsakk.ssgdotcom.purchaseproduct.application;
import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure.PurchaseProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PurchaseProductServiceImp implements PurchaseProductService {

    private final PurchaseProductRepository purchaseProductRepository;
    @Override
    @Transactional
    public void savePurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase) {
        List<PurchaseProduct> purchaseProductList = new ArrayList<>();
        for (PurchaseProductDto purchaseProductDto : purchaseProductDtoList) {
            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                    .purchaseSeq(purchase)
                    .productId(purchaseProductDto.getProductId())
                    .purchaseProductName(purchaseProductDto.getPurchaseProductName())
                    .purchaseProductVendor(purchaseProductDto.getPurchaseProductVendor())
                    .purchaseProductOption(purchaseProductDto.getPurchaseProductOption())
                    .purchaseProductCount(purchaseProductDto.getPurchaseProductCount())
                    .purchaseProductPrice(purchaseProductDto.getPurchaseProductPrice())
                    .purchaseProductDiscountPrice(purchaseProductDto.getPurchaseProductDiscountPrice())
                    .productThumbnail(purchaseProductDto.getProductThumbnail())
                    .deliveryType(String.valueOf(purchaseProductDto.getDeliveryType()))
                    .productState(purchaseProductDto.getProductState())
                    .build();
            purchaseProductList.add(purchaseProduct);
        }
        purchaseProductRepository.saveAll(purchaseProductList);
    }
}
