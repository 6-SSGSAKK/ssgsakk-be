package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.review.dto.ReviewWriteDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewWriteListResponseVo {
    private List<Long> reviewSeq;

    public static ReviewWriteListResponseVo DtoListToVoList(List<ReviewWriteDto> reviewWriteDtoList) {
        return ReviewWriteListResponseVo.builder()
                .reviewSeq(reviewWriteDtoList.stream().map(ReviewWriteDto::getReviewSeq).toList()).build();
    }
}
