package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.product.Vo.*;
import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    // 상품 검색
    @GetMapping("/search")
    public BaseResponse<?> searchProducts(@RequestParam("keyword") String keyword) {
        List<SearchProductDto> searchProductDto = productService.searchProducts(keyword);


        return new BaseResponse<>("searchProduct Success", searchProductDto.stream()
                .map(productDto -> SearchProductResponseVo.builder()
                        .productSeq(productDto.getProductSeq())
                        .productName(productDto.getProductName())
                        .productPrice(productDto.getProductPrice())
                        .build())
                .collect(Collectors.toList()));
    }


    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보 조회", description = "상품의 상세 정보를 조회", tags = { "Product Info" })
    public BaseResponse<?> productInfo(@PathVariable("id") Long id) {
        ProductDto productDto = productService.productInfo(id);

        return new BaseResponse<>("productInfo Success", ProductInfoResponseVo.builder()
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .vendor(productDto.getVendor())
                .productCode(productDto.getProductCode())
                .productDescription(productDto.getProductDescription())
                .discountPercent(productDto.getDiscountPercent())
                .build());
    }


    // 상품 등록
    @PostMapping()
    @Operation(summary = "상품 등록", description = "새로운 상품을 등록", tags = { "Add product" })
    public BaseResponse<?> addProduct(@RequestBody AddProductRequestVo addProductRequestVo) {
        AddProductDto addProductRequestDto = AddProductDto.builder()
                .productName(addProductRequestVo.getProductName())
                .productPrice(addProductRequestVo.getProductPrice())
                .vendor(addProductRequestVo.getVendor())
                .productCode(addProductRequestVo.getProductCode())
                .productDescription(addProductRequestVo.getProductDescription())
                .discountPercent(addProductRequestVo.getDiscountPercent())
                .build();
        AddProductDto addProductResponseDto = productService.addProduct(addProductRequestDto);

        return new BaseResponse<>("addProduct Success", AddProductResponseVo.builder()
                .productName(addProductResponseDto.getProductName()));
    }

    // 상품 수정
    @PutMapping("/{id}")
    @Operation(summary = "상품 수정", description = "기존 상품을 수정", tags = { "Update product" })
    public BaseResponse<?> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductRequestVo updateProductRequestVo) {
        UpdateProductDto updateProductDto = UpdateProductDto.builder()
                .productName(updateProductRequestVo.getProductName())
                .productPrice(updateProductRequestVo.getProductPrice())
                .vendor(updateProductRequestVo.getVendor())
                .productCode(updateProductRequestVo.getProductCode())
                .productDescription(updateProductRequestVo.getProductDescription())
                .discountPercent(updateProductRequestVo.getDiscountPercent())
                .build();
        productService.updateProduct(id, updateProductDto);
        return new BaseResponse<>("updateProduct Success", "");
    }


    // 상품 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제", description = "상품 삭제", tags = { "Delete product" })
    public BaseResponse<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new BaseResponse<>("deleteProduct Success", "");
    }
}
