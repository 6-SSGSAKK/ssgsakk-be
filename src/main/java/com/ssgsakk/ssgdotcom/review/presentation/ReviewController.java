package com.ssgsakk.ssgdotcom.review.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.review.application.ReviewService;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.dto.UpdateReviewDto;
import com.ssgsakk.ssgdotcom.review.vo.*;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final JWTUtil jwtUtil;
    @PutMapping()
    @Operation(summary = "리뷰 작성", description = "리뷰를 작성합니다.", tags = {"Review"})
    public BaseResponse<?> createReview(@RequestHeader("Authorization") String accessToken,
                                        @RequestBody ReviewRequestVo reviewRequestVo) {
        reviewService.createReview(ReviewDto.VoToDto(reviewRequestVo), getUuid(accessToken));
        return new BaseResponse<>("createReview Success","");
    }

    @PutMapping("/{reviewSeq}")
    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.", tags = {"Review"})
    public BaseResponse<?> updateReview(@PathVariable Long reviewSeq,@RequestHeader("Authorization") String accessToken,
                                        @RequestBody UpdateReviewRequestVo updateReviewRequestVo) {
        reviewService.updateReview(reviewSeq, UpdateReviewDto.VoToDto(reviewSeq, updateReviewRequestVo), getUuid(accessToken));
        return new BaseResponse<>("updateReview Success","");
    }

    @DeleteMapping("/{reviewSeq}")
    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.", tags = {"Review"})
    public BaseResponse<?> deleteReview(@PathVariable Long reviewSeq) {
        reviewService.deleteReview(reviewSeq);
        return new BaseResponse<>("deleteReview Success","");
    }

    @GetMapping("/{reviewSeq}")
    @Operation(summary = "리뷰 조회", description = "리뷰를 조회합니다.", tags = {"Review"})
    public BaseResponse<ReviewInfoResponseVo> getReview(@PathVariable Long reviewSeq) {
        return new BaseResponse<>("getReview Success",
                ReviewInfoResponseVo.DtoToVo(reviewService.getReviewInfo(reviewSeq)));
    }

    @GetMapping("/products/{productSeq}")
    @Operation(summary = "상품의 리뷰 조회", description = "상품의 리뷰를 조회합니다.", tags = {"Review"})
    public BaseResponse<List<ReviewListResponseVo>> getReviews(@PathVariable Long productSeq) {
        return new BaseResponse<>("getReviews Success",
                 ReviewListResponseVo.DtoListToVoList(reviewService.getReviewList(productSeq)));
    }

    @GetMapping("/writable")
    @Operation(summary = "사용자의 작성 가능 리뷰 조회", description = "사용자의 작성 가능 리뷰를 조회합니다.", tags = {"Review"})
    public BaseResponse<List<ReviewWritableResponseVo>> getWritableReviews(@RequestHeader("Authorization") String accessToken) {
        return new BaseResponse<>("getWritableReviews Success",
                 ReviewWritableResponseVo.DtoListToVoList(reviewService.getWritableReviewList(getUuid(accessToken))));
    }

    @GetMapping("/written")
    @Operation(summary = "사용자의 작성한 리뷰 조회 ", description = "사용자의 작성한 리뷰 조회", tags = {"Review"})
    public BaseResponse<List<ReviewWrittenResponseVo>> getWrittenReviews(@RequestHeader("Authorization") String accessToken) {
        return new BaseResponse<>("getWrittenReviews Success",
                ReviewWrittenResponseVo.DtoListToVoList(reviewService.getWrittenReviewList(getUuid(accessToken))));
    }

    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
