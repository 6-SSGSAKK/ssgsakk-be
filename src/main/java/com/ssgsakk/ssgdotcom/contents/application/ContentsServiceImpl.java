package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ContentsRepository;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ProductContentsRepository;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ReviewContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {
    private final ProductContentsRepository productContentsRepository;
    private final ReviewContentsRepository reviewContentsRepository;
    private final ContentsRepository contentsRepository;
    @Override
    @Transactional
    public List<ProductContents> productContentsList(Long ProductSeq){
        return productContentsRepository.findByProduct_ProductSeq(ProductSeq);
    }

    @Override
    @Transactional
    public List<ReviewContents> reviewContentsList(Long ReviewSeq) {
        return reviewContentsRepository.findByReview_ReviewSeq(ReviewSeq);
    }

    @Override
    @Transactional
    public void createReviewContents(List<String> contentUrl) {
        for (String url : contentUrl) {
            Contents contents = Contents.builder().contentUrl(url).build();
            contentsRepository.save(contents);
            ReviewContents reviewContents = ReviewContents.builder().contents(contents).build();
            reviewContentsRepository.save(reviewContents);
        }
    }

    @Override
    @Transactional
    public void deleteReviewContents(Long reviewSeq) {
        reviewContentsRepository.deleteById(reviewSeq);
    }
}
