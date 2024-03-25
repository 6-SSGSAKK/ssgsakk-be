package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductContentsRepository extends JpaRepository<ProductContents, Long> {
    List<ProductContents> findByProduct_ProductSeq(Long productSeq);

}
