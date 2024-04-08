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
    @Transactional //주문상품리스트를 생성하는 메소드
    public void savePurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase) {
        //List<PurchaseProductDto>, Purchase를 불러옴 Purchase는 purchaseSeq를 담기 위함
        List<PurchaseProduct> purchaseProductList = new ArrayList<>();
        for (PurchaseProductDto purchaseProductDto : purchaseProductDtoList) {//주문상품이 여러개일수도 있기 때문에 FOR 로 담아줌
            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                    .purchaseSeq(purchase) //Purchase의 PK
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
        purchaseProductRepository.saveAll(purchaseProductList); //저장
    }
}
