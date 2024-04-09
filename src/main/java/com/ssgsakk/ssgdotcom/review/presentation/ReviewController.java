package com.ssgsakk.ssgdotcom.review.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.review.application.ReviewService;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.dto.UpdateReviewDto;
import com.ssgsakk.ssgdotcom.review.vo.ReviewInfoResponseVo;
import com.ssgsakk.ssgdotcom.review.vo.ReviewRequestVo;
import com.ssgsakk.ssgdotcom.review.vo.UpdateReviewRequestVo;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final JWTUtil jwtUtil;
    @PutMapping()
    @Operation(summary = "리뷰 작성", description = "리뷰를 작성합니다.", tags = {"Review"})
    public BaseResponse<?> createReview(@RequestHeader("Authorization") String accessToken,
                                        ReviewRequestVo reviewRequestVo) {
        reviewService.createReview(ReviewDto.VoToDto(reviewRequestVo), getUuid(accessToken));
        return new BaseResponse<>("createReview Success","");
    }

    @PutMapping("/{reviewSeq}")
    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.", tags = {"Review"})
    public BaseResponse<?> updateReview(@PathVariable Long reviewSeq,@RequestHeader("Authorization") String accessToken,
                                        UpdateReviewRequestVo updateReviewRequestVo) {
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
