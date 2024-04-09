package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.dto.ReviewInfoDto;
import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;
import com.ssgsakk.ssgdotcom.review.dto.UpdateReviewDto;

import java.util.Optional;

public interface ReviewService {
    void updateReview(Long reviewSeq,UpdateReviewDto updateReviewDto, String uuid);

    void deleteReview(Long reviewSeq);

    Optional<ReviewListDto> getReviewList(Long productId);

    void createReview(ReviewDto reviewDto, String uuid);

    ReviewInfoDto getReviewInfo(Long reviewSeq);
}
