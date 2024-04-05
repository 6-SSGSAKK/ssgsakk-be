package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;

import java.util.List;
import java.util.Optional;

public interface ContentsService {
    List<ProductContents> productContentsList(Long ProductSeq);

    List<ReviewContents> reviewContentsList(Long ReviewSeq);

    void createReviewContents(String contentUrl);

    void deleteReviewContents(Long reviewSeq);
}
