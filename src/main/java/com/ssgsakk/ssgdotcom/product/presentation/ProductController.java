package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import com.ssgsakk.ssgdotcom.product.vo.*;
import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;

//import com.ssgsakk.ssgdotcom.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ContentsService contentsService;

    // 상품 검색
    @GetMapping("/search")
    @Operation(summary = "상품 검색", description = "상품이름으로 검색", tags = { "Product Search" })
    public BaseResponse<?> searchProducts(@RequestParam("keyword") String keyword) {
        List<SearchProductDto> searchProductDto = productService.searchProducts(keyword);

        return new BaseResponse<>("searchProduct Success", searchProductDto.stream()
                .map(productDto -> SearchProductResponseVo.builder()
                        .productSeq(productDto.getProductSeq())
                        .build())

              .collect(Collectors.toList()));
    }

    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보 조회", description = "상품의 상세 정보를 조회", tags = { "Product Info" })
    public BaseResponse<?> productInfo(@PathVariable("id") Long productSeq) {
        ProductDto productDto = productService.productInfo(productSeq);
        List<ProductContents> contents = contentsService.productContentsList(productSeq);

        List<ProductContentsVo> contentVos = new ArrayList<>();
        if (contents != null) {
            for (ProductContents content : contents) {
                ProductContentsVo contentVo = ProductContentsVo.builder()
                        .priority(content.getPriority())
                        .contentUrl(content.getContents() != null ? content.getContents().getContentUrl() : null)
                        .contentDescription(content.getContents().getContentDescription())
                        .build();
                contentVos.add(contentVo);
            }
        }

        return new BaseResponse<>("productInfo Success", ProductInfoResponseVo.builder()
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .vendor(productDto.getVendor())
                .productDescription(productDto.getProductDescription())
                .discountPercent(productDto.getDiscountPercent())
                .deliveryType(productDto.getDeliveryType())
                .averageRating(productDto.getAverageRating())
                .reviewCount(productDto.getReviewCount())
                .contents(contentVos)
                .build());
    }

}
