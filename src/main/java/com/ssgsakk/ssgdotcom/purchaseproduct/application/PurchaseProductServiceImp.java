package com.ssgsakk.ssgdotcom.purchaseproduct.application;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure.PurchaseProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class PurchaseProductServiceImp implements PurchaseProductService {
    private final PurchaseProductRepository purchaseProductRepository;

    @Override
    public void createPurchaseProduct(PurchaseProductDto purchaseProductDto){

        PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                .purchaseSeq(purchaseProductDto.getPurchaseSeq())
                .productId(purchaseProductDto.getProductId())
                .purchaseProductName(purchaseProductDto.getPurchaseProductName())
                .purchaseProductVendor(purchaseProductDto.getPurchaseProductVendor())
                .purchaseProductOption(purchaseProductDto.getPurchaseProductOption())
                .purchaseProductCount(purchaseProductDto.getPurchaseProductCount())
                .purchaseProductPrice(purchaseProductDto.getPurchaseProductPrice())
                .purchaseProductDiscountPrice(purchaseProductDto.getPurchaseProductDiscountPrice())
                .productThumbnail(purchaseProductDto.getProductThumbnail())
                .deliveryType(purchaseProductDto.getDeliveryType())
                .build();
    }


}
