package com.ssgsakk.ssgdotcom.category.infrastructure;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategorySeq(Long categorySeq);
}


