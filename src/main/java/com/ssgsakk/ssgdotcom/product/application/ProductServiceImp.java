package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    // 모든 상품 조회
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    // 상품 상세 정보 조회
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 상품 추가
    @Override
    public Product addProduct(ProductDto productDto) {
        return productRepository.save(Product.builder()
                        .productName(productDto.getProductName())
                        .productPrice(productDto.getProductPrice())
                        .vendor(productDto.getVendor())
                        .createdAt(productDto.getCreatedAt())
                        .productCode(productDto.getProductCode())
                        .productDescription(productDto.getProductDescription())
                        .discountPercent(productDto.getDiscountPercent())
                        .averageRating(productDto.getAverageRating())
                        .reviewCount(productDto.getReviewCount())
                        .build());
    }


    // 상품 삭제
    @Override
    public void deleteProduct(Long id) { productRepository.deleteById(id);}

    // 상품 검색
    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }
}
