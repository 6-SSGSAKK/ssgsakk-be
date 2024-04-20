package com.ssgsakk.ssgdotcom.review.dto;

import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewWritableDto {
    private Long purchaseSeq;
    private Long purchaseProductSeq;
    private String purchaseCode;
    private LocalDateTime purchaseDate;
    private Long productSeq;
    private String purchaseProductName;
    private String purchaseProductOption;
    private ProductContentsVo productContentsVo;

}
