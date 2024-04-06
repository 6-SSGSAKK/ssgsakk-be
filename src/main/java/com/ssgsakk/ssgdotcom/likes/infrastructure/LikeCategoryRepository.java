package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCategoryRepository extends JpaRepository<LikeCategory, Long> {
}
