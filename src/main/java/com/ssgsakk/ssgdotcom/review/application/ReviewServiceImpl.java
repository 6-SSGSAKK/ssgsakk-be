package com.ssgsakk.ssgdotcom.review.application;

import com.querydsl.core.Tuple;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import com.ssgsakk.ssgdotcom.review.dto.*;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepository;
import com.ssgsakk.ssgdotcom.review.infrastructure.ReviewRepositoryImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ContentsService contentsService;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ReviewRepositoryImpl reviewRepositoryImpl;
    @Override
    @Transactional
    public void createReview(ReviewDto reviewDto, String uuid) {
        User user = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_USER));

        Review review = getEntity(reviewDto, user.getUserId());
        reviewRepository.save(review);
        contentsService.createReviewContents(review, reviewDto.getReviewContentsVoList());
        updateReviewCount(reviewDto);
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
        decreaseReviewCount(review);
        contentsService.deleteReviewContents(reviewSeq);
    }

    @Override
    @Transactional
    public ReviewInfoDto getReviewInfo(Long reviewSeq) {
        Review review = reviewRepository.findById(reviewSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_REVIEW));
        List<ReviewContents> reviewContents = contentsService.reviewContentsList(reviewSeq);
        List<ReviewContentsVo> contentVo = getReviewContentsVo(reviewContents);

        return getReviewInfoDto(review, contentVo);
    }

    @Override
    @Transactional
    public List<ReviewListDto> getReviewList(Long productSeq) {
        List<Review> reviewList = reviewRepository.findAllByProductSeq(productSeq);
        
        return getReviewListDto(reviewList);
    }

    @Override
    @Transactional
    public List<ReviewWritableDto> getWritableReviewList(String uuid) {
        List<PurchaseProduct> purchaseProductList = reviewRepositoryImpl.getWritableReviewProductSeq(uuid);
        return getReviewWritableDtoList(purchaseProductList);
    }

    @Override
    @Transactional
    public List<ReviewWrittenDto> getWrittenReviewList(String uuid) {
        List<Tuple> reviewProductList = reviewRepositoryImpl.getWrittenReviewProductSeq(uuid);
        return reviewProductList.stream()
                .map(reviewProduct -> {
                    PurchaseProduct purchaseProduct = reviewProduct.get(0, PurchaseProduct.class);
                    Review review = reviewProduct.get(1, Review.class);
                    return getReviewWrittenDto(purchaseProduct, review);
                })
                .collect(Collectors.toList());
    }
    private List<ReviewListDto> getReviewListDto(List<Review> reviewList) {
        if (reviewList.isEmpty()) {
            return List.of();
        }
        return getReviewListDtos(reviewList);
    }

    // 빌더 및 리뷰 평점, 카운트 반영 함수

    private static ReviewInfoDto getReviewInfoDto(Review review, List<ReviewContentsVo> contentVo) {
        return ReviewInfoDto.builder()
                .reviewParagraph(review.getReviewParagraph())
                .reviewScore(review.getReviewScore())
                .userId(review.getUserId())
                .reviewDate(review.getCreatedDate())
                .reviewContentsList(contentVo)
                .purchaseProductOption(review.getPurchaseProductOption())
                .build();
    }

    private static List<ReviewWritableDto> getReviewWritableDtoList(List<PurchaseProduct> purchaseProductList) {
        return purchaseProductList.stream()
                .map(purchaseProduct -> ReviewWritableDto.builder()
                        .purchaseProductSeq(purchaseProduct.getPurchaseProductSeq())
                        .purchaseProductOption(purchaseProduct.getPurchaseProductOptionName())
                        .purchaseCode(purchaseProduct.getPurchaseSeq().getPurchaseCode())
                        .purchaseDate(purchaseProduct.getPurchaseSeq().getCreatedDate())
                        .productSeq(purchaseProduct.getProductSeq())
                        .purchaseProductName(purchaseProduct.getPurchaseProductName())
                        .build())
                .collect(Collectors.toList());
    }

    private static ReviewWrittenDto getReviewWrittenDto(PurchaseProduct purchaseProduct, Review review) {
        return ReviewWrittenDto.builder()
                .purchaseSeq(Objects.requireNonNull(purchaseProduct).getPurchaseProductSeq())
                .purchaseProductSeq(purchaseProduct.getPurchaseProductSeq())
                .purchaseCode(purchaseProduct.getPurchaseSeq().getPurchaseCode())
                .purchaseDate(purchaseProduct.getPurchaseSeq().getCreatedDate())
                .productSeq(purchaseProduct.getProductSeq())
                .purchaseProductName(purchaseProduct.getPurchaseProductName())
                .reviewScore(Objects.requireNonNull(review).getReviewScore())
                .reviewParagraph(review.getReviewParagraph())
                .reviewDate(review.getCreatedDate())
                .build();
    }

    private List<ReviewListDto> getReviewListDtos(List<Review> reviewList) {
        return reviewList.stream()
                .map(Review ->
                        ReviewListDto.builder()
                                .reviewSeq(Review.getReviewSeq())
                                .reviewParagraph(Review.getReviewParagraph())
                                .reviewScore(Review.getReviewScore())
                                .userId(Review.getUserId())
                                .reviewDate(Review.getCreatedDate())
                                .reviewContentsList(getReviewContentsVo(contentsService.reviewContentsList(Review.getReviewSeq())))
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
    private void updateReviewCount(ReviewDto reviewDto) {
        Product product = productRepository.findByProductSeq(reviewDto.getProductSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
        Double updateAverageRating =  product.getAverageRating() == 0 ? reviewDto.getReviewScore() :
                (product.getAverageRating() * product.getReviewCount() + reviewDto.getReviewScore())
                        / (product.getReviewCount() + 1);
        Product updateProduct = Product.builder()
                .productSeq(reviewDto.getProductSeq())
                .averageRating(updateAverageRating)
                .soldCount(product.getSoldCount())
                .productPrice(product.getProductPrice())
                .productName(product.getProductName())
                .vendor(product.getVendor())
                .deliveryType(product.getDeliveryType())
                .discountPercent(product.getDiscountPercent())
                .productDescription(product.getProductDescription())
                .reviewCount(product.getReviewCount() + 1)
                .build();
        productRepository.save(updateProduct);
    }

    private void decreaseReviewCount(Review review) {
        Product product = productRepository.findByProductSeq(review.getProductSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
        Double updateAverageRating =  product.getReviewCount() == 1 ? 0:
                (product.getAverageRating() * product.getReviewCount() - review.getReviewScore())
                        / (product.getReviewCount() - 1);
        Product updateProduct = Product.builder()
                .productSeq(review.getProductSeq())
                .averageRating(updateAverageRating)
                .soldCount(product.getSoldCount())
                .productPrice(product.getProductPrice())
                .productName(product.getProductName())
                .vendor(product.getVendor())
                .deliveryType(product.getDeliveryType())
                .discountPercent(product.getDiscountPercent())
                .productDescription(product.getProductDescription())
                .reviewCount(product.getReviewCount() - 1)
                .build();
        productRepository.save(updateProduct);
    }

    private static List<ReviewContentsVo> getReviewContentsVo(List<ReviewContents> contents) {
        return contents.stream()
                .map(reviewContent -> ReviewContentsVo.builder()
                        .contentUrl(reviewContent.getContents().getContentUrl())
                        .contentsDescription(reviewContent.getContents().getContentDescription())
                        .priority(reviewContent.getPriority())
                        .build())
                .collect(Collectors.toList());
    }
}
