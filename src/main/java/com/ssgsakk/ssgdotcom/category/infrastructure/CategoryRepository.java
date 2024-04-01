package com.ssgsakk.ssgdotcom.category.infrastructure;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}


