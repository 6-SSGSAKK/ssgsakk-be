package com.ssgsakk.ssgdotcom.review.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.review.application.ReviewService;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import com.ssgsakk.ssgdotcom.review.vo.ReviewVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
//    @PutMapping
//    @Operation(summary = "리뷰 작성", description = "리뷰를 작성합니다.", tags = {"Review"})
//    public BaseResponse<?> createReview(ReviewVo reviewVo) {
//        reviewService.createReview(ReviewDto.VoToDto(reviewVo));
//        return new BaseResponse<>("createReview Success","");
//    }
}
