package com.ssgsakk.ssgdotcom.review.infrastructure;

import com.ssgsakk.ssgdotcom.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductSeq(Long productSeq);
}
