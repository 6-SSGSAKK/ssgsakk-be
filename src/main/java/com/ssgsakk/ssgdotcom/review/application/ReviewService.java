package com.ssgsakk.ssgdotcom.review.application;

import com.ssgsakk.ssgdotcom.review.dto.*;

import java.util.List;

public interface ReviewService {
    void updateReview(Long reviewSeq,UpdateReviewDto updateReviewDto, String uuid);

    void deleteReview(Long reviewSeq);

    void createReview(ReviewDto reviewDto, String uuid);

    ReviewInfoDto getReviewInfo(Long reviewSeq);

    List<ReviewListDto> getReviewList(Long productSeq);

    List<ReviewWriteDto> getWritableReviewList(String uuid);

    List<ReviewWriteDto> getWrittenReviewList(String uuid);

    //List<String> getThreeContentsUrl(Long productSeq);
}
