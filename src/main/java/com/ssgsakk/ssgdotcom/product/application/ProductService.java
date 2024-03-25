package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;

import java.util.List;

public interface ProductService {
    ProductDto productInfo(Long id);

    List<SearchProductDto> searchProducts(String keyword);

}