package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.dto.UserInforDto;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.dto.ReviewInfoDto;
import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;
import com.ssgsakk.ssgdotcom.review.dto.UpdateReviewDto;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ContentsService contentsService;
    private final AuthService authService;

    @Override
    @Transactional
    public void createReview(ReviewDto reviewDto, String uuid) {
        UserInforDto userInforDto = authService.userInfor(uuid);
        reviewRepository.save(getEntity(reviewDto, userInforDto.getUserId()));
        contentsService.createReviewContents(reviewDto.getContentsUrl());
        //productRepository.increaseReviewCount(productId);
    }

    @Override
    @Transactional
    public void updateReview(Long reviewSeq, UpdateReviewDto updateReviewDto, String uuid) {
        Review reviewToUpdate = reviewRepository.findById(updateReviewDto.getReviewSeq())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        Review updatedReview = updateEntity(updateReviewDto);
        reviewRepository.save(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
        //productRepository.decreaseReviewCount(productId);
        contentsService.deleteReviewContents(reviewSeq);
    }

    @Override
    @Transactional
    public ReviewInfoDto getReviewInfo(Long reviewSeq) {
        Review review = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_REVIEW));

        return ReviewInfoDto.builder()
                .reviewParagraph(review.getReviewParagraph())
                .reviewScore(review.getReviewScore())
                .userId(review.getUserId())
                .reviewDate(review.getCreatedDate())
                .contentsUrl(contentsService.reviewContentsList(reviewSeq))
                .purchaseProductOption(review.getPurchaseProductOption())
                .build();
    }
    @Override
    @Transactional
    public Optional<ReviewListDto> getReviewList(Long productId) {
        return Optional.empty();
    }
    private static Review getEntity(ReviewDto reviewDto, String userId) {
        return Review.builder()
                .purchaseProductSeq(reviewDto.getPurchaseProductSeq())
                .productSeq(reviewDto.getProductSeq())
                .reviewParagraph(reviewDto.getReviewParagraph())
                .reviewScore(reviewDto.getReviewScore())
                .userId(userId)
                .build();
    }
    private static Review updateEntity(UpdateReviewDto updateReviewDto) {
        return Review.builder()
                .reviewSeq(updateReviewDto.getReviewSeq())
                .reviewParagraph(updateReviewDto.getReviewParagraph())
                .reviewScore(updateReviewDto.getReviewScore())
                .build();
    }
}
