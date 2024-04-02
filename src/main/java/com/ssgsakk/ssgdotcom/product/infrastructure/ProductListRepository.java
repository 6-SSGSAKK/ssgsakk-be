package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.ssgsakk.ssgdotcom.product.domain.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductListRepository extends JpaRepository<ProductList, Long> {
}
