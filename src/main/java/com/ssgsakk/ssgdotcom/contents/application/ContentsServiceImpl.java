package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ContentsRepository;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ProductContentsRepository;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ReviewContentsRepository;
import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import com.ssgsakk.ssgdotcom.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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
    public List<ReviewContentsVo> reviewContentsList(Long ReviewSeq) {
        List<ReviewContents> reviewContents =  reviewContentsRepository.findByReview_ReviewSeq(ReviewSeq);
        return reviewContents.stream().map(reviewContents1 -> ReviewContentsVo.builder()
                .contentUrl(reviewContents1.getContents().getContentUrl())
                .priority(reviewContents1.getPriority())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createReviewContents(Review review, List<ReviewContentsVo> reviewContentsVoList) {
        for (ReviewContentsVo reviewContentsVo : reviewContentsVoList) {
            Contents contents = Contents.builder()
                    .contentUrl(reviewContentsVo.getContentUrl())
                    .build();
            contentsRepository.save(contents);
            ReviewContents reviewContents = ReviewContents.builder()
                    .review(review)
                    .contents(contents)
                    .priority(reviewContentsVo.getPriority())
                    .build();
            reviewContentsRepository.save(reviewContents);
        }
    }

    @Override
    @Transactional
    public void deleteReviewContents(Long reviewSeq) {
        reviewContentsRepository.deleteById(reviewSeq);
    }
}
