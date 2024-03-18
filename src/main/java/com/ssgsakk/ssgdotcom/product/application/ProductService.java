package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    ProductDto productInfo(Long id);

    List<SearchProductDto> searchProducts(String keyword);

    void updateProduct(Long id, UpdateProductDto updateProductDto);

    AddProductDto addProduct(AddProductDto addProductDto);

    void deleteProduct(Long id);

}