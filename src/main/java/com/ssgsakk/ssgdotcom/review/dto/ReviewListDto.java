package com.ssgsakk.ssgdotcom.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewListDto {
    private List<Long> reviewSeq;
}
