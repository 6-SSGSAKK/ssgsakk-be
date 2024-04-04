package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import com.ssgsakk.ssgdotcom.event.domain.EventProduct;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventProductRepository;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductFilterDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductListInfoDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryimpl;
    private final EventProductRepository eventProductRepository;
    private final EventRepository eventRepository;
    private final ContentsService contentsService;
    // 상품 상세 정보 조회
    @Override
    @Transactional
    public ProductDto productInfo(Long productSeq) {
        Product product = productRepository.findById(productSeq)
                .orElseThrow(() -> new RuntimeException("찾으시는 상품이 없습니다."));
        List<ProductContents> contents = contentsService.productContentsList(productSeq);
        List<ProductContentsVo> contentVos = getProductContentsVos(contents);
        return getProductInfoDto(product, contentVos);
    }


    @Override
    public ProductListInfoDto productListInfo(Long productSeq) {
        Product product = productRepository.findById(productSeq)
                .orElseThrow(() -> new RuntimeException("찾으시는 상품이 없습니다."));
        List<ProductContents> contents = contentsService.productContentsList(productSeq);
        ProductContentsVo contentVo = getProductContentsVo(contents);
        return getProductListInfoDto(product, contentVo);
    }


    // 상품 검색
    @Override
    @Transactional
    public List<SearchProductDto> searchProducts(ProductFilterDto productFilterDto) {
        List<Long> products = productRepositoryimpl.productFilter(productFilterDto);

        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product)
                        .build())
                .collect(Collectors.toList());
        }
    @Override
    @Transactional
    public List<SearchProductDto> productEvent(Long eventSeq) {
        eventRepository.findById(eventSeq).orElseThrow(() -> new RuntimeException("존재하지 않는 이벤트 입니다."));
        List<EventProduct> eventProductList = eventProductRepository.findByEvent_EventSeq(eventSeq);
        return eventProductList.stream()
                .map(eventProduct -> SearchProductDto.builder()
                        .productSeq(eventProduct.getProduct().getProductSeq())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SearchProductDto> productBest(ProductFilterDto productFilterDto) {
        List<Product> products = productRepositoryimpl.bestProduct(productFilterDto);
        return products.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product.getProductSeq())
                        .build())
                .collect(Collectors.toList());
    }


    private static ProductDto getProductInfoDto(Product product, List<ProductContentsVo> contentVos) {
        return ProductDto.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .vendor(product.getVendor().getVendorName())
                .productDescription(product.getProductDescription())
                .discountPercent(product.getDiscountPercent())
                .averageRating(product.getAverageRating())
                .deliveryType(product.getDeliveryType())
                .reviewCount(product.getReviewCount())
                .contentsUrl(contentVos)
                .build();
    }

    private static List<ProductContentsVo> getProductContentsVos(List<ProductContents> contents) {
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
        return contentVos;
    }
    private static ProductContentsVo getProductContentsVo(List<ProductContents> contents) {
        ProductContentsVo contentVo = null;
        if (contents != null) {
            for (ProductContents content : contents) {
                contentVo = ProductContentsVo.builder()
                        .priority(content.getPriority())
                        .contentUrl(content.getContents() != null ? content.getContents().getContentUrl() : null)
                        .contentDescription(content.getContents().getContentDescription())
                        .build();
                if (content.getPriority() == 1) break;
            }
        }
        return contentVo;
    }
    private static ProductListInfoDto getProductListInfoDto(Product product, ProductContentsVo contentVo) {
        return ProductListInfoDto.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .vendor(product.getVendor().getVendorName())
                .discountPercent(product.getDiscountPercent())
                .averageRating(product.getAverageRating())
                .deliveryType(product.getDeliveryType())
                .reviewCount(product.getReviewCount())
                .contentsUrl(contentVo)
                .build();
    }
}
