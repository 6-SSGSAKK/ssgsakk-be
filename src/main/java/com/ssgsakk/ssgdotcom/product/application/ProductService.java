package com.ssgsakk.ssgdotcom.product.application;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(ProductDto productDto);

    Product deleteProduct(Long id);
}
