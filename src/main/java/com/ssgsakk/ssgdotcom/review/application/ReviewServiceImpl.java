package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ContentsService contentsService;

    @Override
    @Transactional
    public void createReview(ReviewDto reviewDto) {
        reviewRepository.save(getEntity(reviewDto));
        contentsService.createReviewContents(reviewDto.getContentsUrl());
        //productRepository.increaseReviewCount(productId);
    }

    @Override
    @Transactional
    public void updateReview(Long reviewSeq, String reviewParagraph, Short reviewScore) {
        Review reviewToUpdate = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Review updatedReview = updateEntity(reviewToUpdate, ReviewDto.builder()
                .reviewParagraph(reviewParagraph).reviewScore(reviewScore).build());
        reviewRepository.save(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long productId,Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
        //productRepository.decreaseReviewCount(productId);
        contentsService.deleteReviewContents(reviewSeq);
    }

    @Override
    @Transactional
    public Optional<ReviewListDto> getReviewList(Long productId) {
        return Optional.empty();
    }
    private static Review getEntity(ReviewDto reviewDto) {
        return Review.builder()
                .productSeq(reviewDto.getProductSeq())
                .reviewParagraph(reviewDto.getReviewParagraph())
                .reviewScore(reviewDto.getReviewScore())
                .build();
    }
    private static Review updateEntity(Review review, ReviewDto reviewDto) {
        return Review.builder()
                .reviewSeq(review.getReviewSeq())
                .productSeq(reviewDto.getProductSeq())
                .reviewParagraph(reviewDto.getReviewParagraph())
                .reviewScore(reviewDto.getReviewScore())
                .build();
    }
}
