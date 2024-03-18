package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
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

    // 상품 상세 정보 조회
    @Override
    public ProductDto productInfo(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾으시는 상품이 없습니다."));

        return ProductDto.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .vendor(product.getVendor())
                .productCode(product.getProductCode())
                .productDescription(product.getProductDescription())
                .discountPercent(product.getDiscountPercent())
                .build();
    }


    // 상품 등록
    @Override
    public AddProductDto addProduct(AddProductDto addProductDto) {

        productRepository.save(Product.builder()
                .productName(addProductDto.getProductName())
                .productPrice(addProductDto.getProductPrice())
                .vendor(addProductDto.getVendor())
                .productCode(addProductDto.getProductCode())
                .productDescription(addProductDto.getProductDescription())
                .discountPercent(addProductDto.getDiscountPercent())
                .build());
        return addProductDto;
    }

    // 상품 수정
    @Override
    public void updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾으시는 상품이 없습니다."));

        existingProduct.setProductName(updateProductDto.getProductName());
        existingProduct.setProductPrice(updateProductDto.getProductPrice());
        existingProduct.setVendor(updateProductDto.getVendor());
        existingProduct.setProductCode(updateProductDto.getProductCode());
        existingProduct.setProductDescription(updateProductDto.getProductDescription());
        existingProduct.setDiscountPercent(updateProductDto.getDiscountPercent());

    }


    // 상품 삭제
    @Override
    public void deleteProduct(Long id) { productRepository.deleteById(id);}

    // 상품 검색
    // entity 전체로 받아서 dto에서 처리 JPA에선 그렇게 자주 함.
    @Override
    public List<SearchProductDto> searchProducts(String keyword) {
        List<Product> products = productRepository.findByProductNameContaining(keyword);

        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product.getProductSeq())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .build())
                .collect(Collectors.toList());
        }
}
