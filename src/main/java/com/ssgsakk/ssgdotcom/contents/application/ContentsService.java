package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import com.ssgsakk.ssgdotcom.review.domain.Review;

import java.util.List;

public interface ContentsService {
    List<ProductContents> productContentsList(Long ProductSeq);

    List<ReviewContents> reviewContentsList(Long ReviewSeq);

    void createReviewContents(Review review, List<ReviewContentsVo> reviewContentsVoList);

    void deleteReviewContents(Long reviewSeq);

    boolean checkReviewContents(Long reviewSeq);
}
