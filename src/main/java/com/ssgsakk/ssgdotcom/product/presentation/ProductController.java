package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private ProductService productService;

    // 모든 상품 조회
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "상품 상세 정보 조회", description = "상품 상세 정보 조회", tags = { "product info" })
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) { return productService.getProductById(id);}

    // 상품 추가
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductDto productDto) { return productService.addProduct(productDto); }


    // 상품 삭제
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) { return productService.deleteProduct(id); }
}
