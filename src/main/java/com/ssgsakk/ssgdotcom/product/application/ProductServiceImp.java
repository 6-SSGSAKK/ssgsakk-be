package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.event.domain.EventProduct;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventProductRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductFilterDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryimpl;
    private final EventProductRepository eventProductRepository;
    // 상품 상세 정보 조회
    @Override
    @Transactional
    public ProductDto productInfo(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾으시는 상품이 없습니다."));

        return ProductDto.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .vendor(product.getVendor().getVendorName())
                .productDescription(product.getProductDescription())
                .discountPercent(product.getDiscountPercent())
                .averageRating(product.getAverageRating())
                .deliveryType(product.getDeliveryType())
                .reviewCount(product.getReviewCount())
                .build();
    }

    // 상품 검색
    @Override
    @Transactional
    public List<SearchProductDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByProductNameContaining(keyword);

        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product.getProductSeq())
                        .build())
                .collect(Collectors.toList());
        }
    @Override
    @Transactional
    public List<SearchProductDto> productEvent(Long eventSeq) {
        List<EventProduct> eventProductList = eventProductRepository.findByEvent_EventSeq(eventSeq);
        return eventProductList.stream()
                .map(eventProduct -> SearchProductDto.builder()
                        .productSeq(eventProduct.getProduct().getProductSeq())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchProductDto> productBest(DeliveryType deliveryType) {
        List<Long> products = productRepositoryimpl.productbest(deliveryType);
        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SearchProductDto> productFilter(ProductFilterDto productFilterDto) {
        //productRepositoryimpl.findByDeliveryType()
        return null;
    }
}
