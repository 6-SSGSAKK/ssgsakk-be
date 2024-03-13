package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.AddProductDto;
import com.ssgsakk.ssgdotcom.product.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> searchProducts(String keyword);
    Product updateProduct(Long id, UpdateProductDto updateProductDto);
    void addProduct(AddProductDto addProductDto);
    void deleteProduct(Long id);

}
