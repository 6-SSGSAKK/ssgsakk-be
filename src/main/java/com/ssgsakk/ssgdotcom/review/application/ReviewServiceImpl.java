package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.dto.UserInforDto;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import com.ssgsakk.ssgdotcom.review.dto.*;

import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepository;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ContentsService contentsService;
    private final AuthService authService;
    private final ProductRepository productRepository;
    private final ReviewRepositoryImpl reviewRepositoryImpl;

    @Override
    @Transactional
    public void createReview(ReviewDto reviewDto, String uuid) {
        UserInforDto userInforDto = authService.userInfor(uuid);
        Review review = getEntity(reviewDto, userInforDto.getUserId());
        reviewRepository.save(review);
        contentsService.createReviewContents(review, reviewDto.getReviewContentsVoList());
        updateReviewCount(reviewDto.getProductSeq());
    }

    @Override
    @Transactional
    public void updateReview(Long reviewSeq, UpdateReviewDto updateReviewDto, String uuid) {
        Review reviewToUpdate = reviewRepository.findById(updateReviewDto.getReviewSeq())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        Review updatedReview = updateEntity(reviewToUpdate, updateReviewDto);
        reviewRepository.save(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewSeq) {
        Review review = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_REVIEW));
        reviewRepository.deleteById(reviewSeq);
        decreaseReviewCount(review.getProductSeq());
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
                .reviewContentsVoList(contentsService.reviewContentsList(reviewSeq))
                .purchaseProductOption(review.getPurchaseProductOption())
                .build();
    }

    @Override
    @Transactional
    public List<ReviewListDto> getReviewList(Long productSeq) {
        List<Review> reviewList = reviewRepository.findAllByProductSeq(productSeq);
        
        return getReviewListDto(reviewList);
    }

    @Override
    @Transactional
    public List<ReviewWriteDto> getWritableReviewList(String uuid) {
        List<Long> reviewList = reviewRepositoryImpl.getWritableReviewProductSeq(uuid);
        return reviewList.stream()
                .map(reviewSeq -> ReviewWriteDto.builder()
                        .reviewSeq(reviewSeq)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReviewWriteDto> getWrittenReviewList(String uuid) {
        List<Long> reviewList = reviewRepositoryImpl.getWrittenReviewProductSeq(uuid);
        return reviewList.stream()
                .map(reviewSeq -> ReviewWriteDto.builder()
                        .reviewSeq(reviewSeq)
                        .build())
                .collect(Collectors.toList());
    }

    private List<ReviewListDto> getReviewListDto(List<Review> reviewList) {
        return reviewList.stream()
                .map(Review -> ReviewListDto.builder()
                        .reviewSeq(Review.getReviewSeq())
                        .reviewParagraph(Review.getReviewParagraph())
                        .reviewScore(Review.getReviewScore())
                        .userId(Review.getUserId())
                        .reviewDate(Review.getCreatedDate())
                        .reviewContentsVoList(contentsService.reviewContentsList(Review.getReviewSeq()))
                        .purchaseProductOption(Review.getPurchaseProductOption())
                        .build())
                .collect(Collectors.toList());
    }

    private static Review getEntity(ReviewDto reviewDto, String userId) {
        // 리뷰 작성시 실제 구매했는지 확인해야함
        String maskedUserId = userId.substring(0, 3) + "****";
        return Review.builder()
                .purchaseProductSeq(reviewDto.getPurchaseProductSeq())
                .productSeq(reviewDto.getProductSeq())
                .reviewParagraph(reviewDto.getReviewParagraph())
                .reviewScore(reviewDto.getReviewScore())
                .userId(maskedUserId)
                .purchaseProductOption(reviewDto.getPurchaseProductOption())
                .build();
    }
    private static Review updateEntity(Review reviewUpdate, UpdateReviewDto updateReviewDto) {
        return Review.builder()
                .purchaseProductSeq(reviewUpdate.getPurchaseProductSeq())
                .productSeq(reviewUpdate.getProductSeq())
                .userId(reviewUpdate.getUserId())
                .purchaseProductOption(reviewUpdate.getPurchaseProductOption())
                .reviewSeq(updateReviewDto.getReviewSeq())
                .reviewParagraph(updateReviewDto.getReviewParagraph())
                .reviewScore(updateReviewDto.getReviewScore())
                .build();
    }
    private void updateReviewCount(Long productId) {
        Product product = productRepository.findByProductSeq(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
        Product updateProduct = Product.builder()
                .productSeq(productId)
                .reviewCount(product.getReviewCount() + 1)
                .build();
        productRepository.save(updateProduct);
    }

    private void decreaseReviewCount(Long productId) {
        Product product = productRepository.findByProductSeq(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
        Product updateProduct = Product.builder()
                .productSeq(productId)
                .reviewCount(product.getReviewCount() - 1)
                .build();
        productRepository.save(updateProduct);
    }
}
