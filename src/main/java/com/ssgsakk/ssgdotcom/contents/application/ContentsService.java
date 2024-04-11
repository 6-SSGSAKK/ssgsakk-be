package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.review.domain.Review;

import java.util.List;

public interface ContentsService {
    List<ProductContents> productContentsList(Long ProductSeq);

    List<String> reviewContentsList(Long ReviewSeq);

    void createReviewContents(Review review, List<String> contentUrl);

    void deleteReviewContents(Long reviewSeq);
}
