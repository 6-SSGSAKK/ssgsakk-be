package com.ssgsakk.ssgdotcom.product.application;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.vendor.domain.Vendor;
import com.ssgsakk.ssgdotcom.vendor.infrastructure.VendorRepository;
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
    private final VendorRepository vendorRepository;
    private final JPAQueryFactory jpaQueryFactory;
    // 상품 상세 정보 조회
    @Override
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
    // entity 전체로 받아서 dto에서 처리 JPA에선 그렇게 자주 함.
    @Override
    public List<SearchProductDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByProductNameContaining(keyword);

        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product.getProductSeq())
                        .build())
                .collect(Collectors.toList());
        }
}
