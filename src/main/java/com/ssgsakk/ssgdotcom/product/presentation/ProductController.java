package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    // 모든 상품 조회
    @GetMapping()
    @Operation(summary = "모든 상품 조회", description = "모든 상품을 조회", tags = { "Search All products" })
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // 상품 검색
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보 조회", description = "상품의 상세 정보를 조회", tags = { "product info" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 조회 성공"),
            @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    })
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상품 추가
    @PostMapping()
    @Operation(summary = "상품 추가", description = "새로운 상품을 추가", tags = { "Add product" })
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product createdProduct = productService.addProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제", description = "상품 삭제", tags = { "Delete product" })
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
