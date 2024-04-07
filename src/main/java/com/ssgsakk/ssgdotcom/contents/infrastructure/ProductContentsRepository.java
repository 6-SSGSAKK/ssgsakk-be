package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductContentsRepository extends JpaRepository<ProductContents, Long> {
    List<ProductContents> findByProduct_ProductSeq(Long productSeq);

    Optional<ProductContents> findByProductAndPriority(Product product, Integer priority);
}
