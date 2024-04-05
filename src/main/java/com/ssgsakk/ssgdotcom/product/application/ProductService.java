package com.ssgsakk.ssgdotcom.product.application;


import com.ssgsakk.ssgdotcom.product.dto.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto productInfo(Long productSeq);

    ProductListInfoDto productListInfo(Long productSeq);

    List<SearchProductDto> searchProducts(ProductFilterDto productFilterDto);

    List<SearchProductDto> filterProducts(ProductFilterDto productFilterDto);

    List<SearchProductDto> productEvent(Long eventSeq);

    List<SearchProductDto> productBest(ProductFilterDto productFilterDto);

}