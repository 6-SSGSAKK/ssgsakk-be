package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String keyword);
}