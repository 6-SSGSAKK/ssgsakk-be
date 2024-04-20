package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductSeq(Long productSeq);
}
