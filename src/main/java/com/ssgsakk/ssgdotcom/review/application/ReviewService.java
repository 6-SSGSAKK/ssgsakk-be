package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;

import java.util.Optional;

public interface ReviewService {

    void createReview(Long productId, String userId, String reviewParagraph, Short reviewScore, String contentUrl);

    void updateReview(Long reviewSeq, String reviewParagraph, Short reviewScore);

    void deleteReview(Long productId,Long reviewSeq);

    Optional<ReviewListDto> getReviewList(Long productId);
}
