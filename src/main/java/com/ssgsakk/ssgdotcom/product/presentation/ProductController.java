package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom._core.errors.exception.Exception404;
import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;
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
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 상품 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }


    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보 조회", description = "상품의 상세 정보를 조회", tags = { "product info" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 조회 성공"),
            @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    })
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception404 e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 추가
    @PostMapping()
    @Operation(summary = "상품 추가", description = "새로운 상품을 추가", tags = { "Add product" })
    public ResponseEntity<?> addProduct(@RequestBody AddProductDto addProductDto) {
        productService.addProduct(addProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addProductDto);
    }

    // 상품 수정
    @PutMapping("/{id}")
    @Operation(summary = "상품 수정", description = "기존 상품을 수정", tags = { "Update product" })
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductDto updateProductDto) {
        Product updatedProduct = productService.updateProduct(id, updateProductDto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제", description = "상품 삭제", tags = { "Delete product" })
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("상품이 성공적으로 삭제되었습니다.");
    }
}
