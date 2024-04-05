package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ContentsService contentsService;

    @Override
    public void createReview(Long productId, String userId, String reviewParagraph, Short reviewScore, String contentUrl) {
        reviewRepository.save(getEntity(productId, userId, reviewParagraph, reviewScore));
        contentsService.createReviewContents(contentUrl);
        //productRepository.increaseReviewCount(productId);
    }

    @Override
    public void updateReview(Long reviewSeq, String reviewParagraph, Short reviewScore) {

    }

    @Override
    public void deleteReview(Long productId,Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
        //productRepository.decreaseReviewCount(productId);
        contentsService.deleteReviewContents(reviewSeq);
    }

    @Override
    public Optional<ReviewListDto> getReviewList(Long productId) {
        return Optional.empty();
    }
    private static Review getEntity(Long productId, String userId, String reviewParagraph, Short reviewScore) {
        return Review.builder().productSeq(productId).userId(userId).reviewParagraph(reviewParagraph)
                .reviewScore(reviewScore).build();
    }
}
