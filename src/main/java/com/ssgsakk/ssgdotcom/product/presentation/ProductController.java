package com.ssgsakk.ssgdotcom.product.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.product.dto.ProductFilterDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductListInfoDto;
import com.ssgsakk.ssgdotcom.product.vo.*;
import com.ssgsakk.ssgdotcom.product.application.ProductService;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // 상품 검색
    @GetMapping("/search")
    @Operation(summary = "상품 검색", description = "상품이름으로 검색", tags = {"Product Search"})
    public BaseResponse<?> searchProducts(@RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "deliveryType", required = false) DeliveryType deliveryType,
                                          @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                          @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                          @RequestParam(value = "categorySeq", required = false) Long categorySeq) {
        ProductFilterDto productFilterDto = ProductFilterDto.ToDto(keyword, deliveryType, minPrice, maxPrice, categorySeq);
        List<SearchProductDto> searchProductDto = productService.searchProducts(productFilterDto);

        return new BaseResponse<>("searchProduct Success", searchProductDto.stream()
                .map(productDto -> SearchProductResponseVo.builder()
                        .productSeq(productDto.getProductSeq())
                        .build())

                .collect(Collectors.toList()));
    }

    @GetMapping("/filter")
    @Operation(summary = "상품 필터링", description = "상품 필터링", tags = { "Product Filtering" })
    public BaseResponse<?> filterProducts(@RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "deliveryType", required = false) DeliveryType deliveryType,
                                          @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                          @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                          @RequestParam(value = "categorySeq", required = false) Long categorySeq) {
        ProductFilterDto productFilterDto = ProductFilterDto.ToDto(keyword, deliveryType, minPrice, maxPrice, categorySeq);
        List<SearchProductDto> searchProductDto = productService.filterProducts(productFilterDto);

        return new BaseResponse<>("filtering product success", searchProductDto.stream()
                .map(productDto -> SearchProductResponseVo.builder()
                        .productSeq(productDto.getProductSeq())
                        .build())

                .collect(Collectors.toList()));
    }


    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보 조회", description = "상품의 상세 정보를 조회", tags = {"Product Info"})
    public BaseResponse<?> productInfo(@PathVariable("id") Long productSeq) {
        ProductDto productDto = productService.productInfo(productSeq);

        return new BaseResponse<>("productInfo Success", ProductInfoResponseVo.VoToDto(productDto));
    }

    // 상품 상세 정보 조회
    @GetMapping("/productsListCard/{id}")
    @Operation(summary = "상품 리스트 정보 조회", description = "상품의 리스트 정보 조회", tags = {"Product List Info"})
    public BaseResponse<?> productListInfo(@PathVariable("id") Long productSeq) {
        ProductListInfoDto productListInfoDto = productService.productListInfo(productSeq);

        return new BaseResponse<>("productInfo Success", ProductListInfoResponseVo.VoToDto(productListInfoDto));
    }

    // 상품 조회
    @GetMapping("/event/{id}")
    @Operation(summary = "묶음 상품", description = "이벤트 묶음 상품", tags = {"Event product"})
    public BaseResponse<?> productEvent(@PathVariable("id") Long eventSeq) {
        List<SearchProductDto> searchProductDtoList = productService.productEvent(eventSeq);
        return new BaseResponse<>("eventProduct Success", SearchProductResponseVo.DtoToVo(searchProductDtoList));
    }

    // 베스트 상품
    @GetMapping("/best")
    @Operation(summary = "최고 순위 상품", description = "최고 순위 상품", tags = {"Best product"})
    public BaseResponse<?> productBest(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "deliveryType", required = false) DeliveryType deliveryType,
                                       @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                       @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                       @RequestParam(value = "categorySeq", required = false) Long categorySeq) {
        ProductFilterDto productFilterDto = ProductFilterDto.ToDto(keyword, deliveryType, minPrice, maxPrice, categorySeq);
        List<SearchProductDto> searchProductDtoList = productService.productBest(productFilterDto);

        return new BaseResponse<>("bestProduct Success", SearchProductResponseVo.DtoToVo(searchProductDtoList));
    }
}
