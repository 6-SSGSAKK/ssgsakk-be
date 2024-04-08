package com.ssgsakk.ssgdotcom.review.infrastructure;

import com.ssgsakk.ssgdotcom.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
