package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom._core.errors.exception.Exception404;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
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
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception404("찾으시는 상품이 없습니다."));
    }

    // 상품 추가
    @Override
    public void addProduct(AddProductDto addProductDto) {
        if (addProductDto.getProductName() == null ||
                addProductDto.getProductPrice() == null ||
                addProductDto.getVendor() == null ||
                addProductDto.getProductCode() == null ||
                addProductDto.getProductDescription() == null
        ) {
            throw new IllegalArgumentException("상품 추가에 필요한 필수 정보가 누락되었습니다.");
        }
        productRepository.save(Product.builder()
                .productName(addProductDto.getProductName())
                .productPrice(addProductDto.getProductPrice())
                .vendor(addProductDto.getVendor())
                .productCode(addProductDto.getProductCode())
                .productDescription(addProductDto.getProductDescription())
                .discountPercent(addProductDto.getDiscountPercent())
                .build());
    }

    // 상품 수정
    @Override
    public Product updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setProductName(updateProductDto.getProductName());
        existingProduct.setProductPrice(updateProductDto.getProductPrice());
        existingProduct.setVendor(updateProductDto.getVendor());
        existingProduct.setProductCode(updateProductDto.getProductCode());
        existingProduct.setProductDescription(updateProductDto.getProductDescription());
        existingProduct.setDiscountPercent(updateProductDto.getDiscountPercent());

        productRepository.save(existingProduct);
        return existingProduct;
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
