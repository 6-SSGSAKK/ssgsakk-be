package com.ssgsakk.ssgdotcom.purchaseproduct.application;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductListDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductStateDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure.PurchaseProductRepository;
import com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure.PurchaseProductRepositoryImp;
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
    private final PurchaseProductRepositoryImp purchaseProductRepositoryImp;
    @Override
    @Transactional //주문상품리스트를 생성하는 메소드
    public void savePurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase) {
        //List<PurchaseProductDto>, Purchase를 불러옴 Purchase는 purchaseSeq를 담기 위함
        List<PurchaseProduct> purchaseProductList = new ArrayList<>();
        Long productseq = null;
        Integer purchaseProductCount = null;
        for (PurchaseProductDto purchaseProductDto : purchaseProductDtoList) {//주문상품이 여러개일수도 있기 때문에 FOR 로 담아줌
            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                    .purchaseSeq(purchase) //Purchase의 PK
                    .productSeq(purchaseProductDto.getProductSeq())
                    .purchaseProductName(purchaseProductDto.getPurchaseProductName())
                    .purchaseProductVendor(purchaseProductDto.getPurchaseProductVendor())
                    .productOptionSeq(purchaseProductDto.getProductOptionSeq())
                    .purchaseProductCount(purchaseProductDto.getPurchaseProductCount())
                    .purchaseProductPrice(purchaseProductDto.getPurchaseProductPrice())
                    .purchaseProductOptionName(purchaseProductDto.getPurchaseProductOptionName())
                    .purchaseProductDiscountPrice(purchaseProductDto.getPurchaseProductDiscountPrice())
                    .productThumbnail(purchaseProductDto.getProductThumbnail())
                    .deliveryType(String.valueOf(purchaseProductDto.getDeliveryType()))
                    .productState(purchaseProductDto.getProductState())
                    .build();
            purchaseProductList.add(purchaseProduct);

            productseq = purchaseProductDto.getProductSeq();
            purchaseProductCount = purchaseProductDto.getPurchaseProductCount();
        }
        purchaseProductRepository.saveAll(purchaseProductList); //저장
        purchaseProductRepositoryImp.decreaseProductStock(productseq, purchaseProductCount);
        purchaseProductRepositoryImp.increaseProductSoldCount(productseq);


    }

    @Override
    @Transactional
    public void savedNonMemberPurchaseProductList(List<PurchaseProductDto> purchaseProductDtoList, Purchase purchase) {

        List<PurchaseProduct> purchaseProductList = new ArrayList<>();
        Long productseq = null;
        Integer purchaseProductCount = null;
        for (PurchaseProductDto purchaseProductDto : purchaseProductDtoList) {//주문상품이 여러개일수도 있기 때문에 FOR 로 담아줌
            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                    .purchaseSeq(purchase) //Purchase의 PK
                    .productSeq(purchaseProductDto.getProductSeq())
                    .purchaseProductName(purchaseProductDto.getPurchaseProductName())
                    .purchaseProductVendor(purchaseProductDto.getPurchaseProductVendor())
                    .productOptionSeq(purchaseProductDto.getProductOptionSeq())
                    .purchaseProductCount(purchaseProductDto.getPurchaseProductCount())
                    .purchaseProductPrice(purchaseProductDto.getPurchaseProductPrice())
                    .purchaseProductOptionName(purchaseProductDto.getPurchaseProductOptionName())
                    .purchaseProductDiscountPrice(purchaseProductDto.getPurchaseProductDiscountPrice())
                    .productThumbnail(purchaseProductDto.getProductThumbnail())
                    .deliveryType(String.valueOf(purchaseProductDto.getDeliveryType()))
                    .productState(purchaseProductDto.getProductState())
                    .build();
            purchaseProductList.add(purchaseProduct);

            productseq = purchaseProductDto.getProductSeq();
            purchaseProductCount = purchaseProductDto.getPurchaseProductCount();
        }
        purchaseProductRepository.saveAll(purchaseProductList); //저장
        purchaseProductRepositoryImp.decreaseProductStock(productseq, purchaseProductCount);
        purchaseProductRepositoryImp.increaseProductSoldCount(productseq);

    }


    @Transactional
    @Override //주문상품 상태 수정 메소드
    public void updatePurchaseProductState(Long purchaseProductSeq, PurchaseProductStateDto purchaseProductStateDto) {
        purchaseProductRepositoryImp.updateProductState(purchaseProductSeq, purchaseProductStateDto);
    }

    @Transactional
    @Override //회원주문상품 상세 조회 메소드
    public List<PurchaseProductListDto> memberPurchaseProductDetail(Long purchaseSeq){
        List<PurchaseProductListDto> PurchaseProductListDto = purchaseProductRepositoryImp.memberPurchaseProductDetail(purchaseSeq);
        return PurchaseProductListDto;
    }





}
