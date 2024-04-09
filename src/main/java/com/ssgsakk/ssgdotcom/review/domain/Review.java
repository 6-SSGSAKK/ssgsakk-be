package com.ssgsakk.ssgdotcom.review.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Review extends BaseCreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewSeq;

    @Column(nullable = false, length = 50)
    private Long productSeq;

    @Column(nullable = false, length = 50)
    private Long purchaseProductSeq;

    @Column(nullable = false, length = 50)
    private String purchaseProductOption;

    @Column(nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reviewParagraph;

    @Column(nullable = false, length = 1)
    private Short reviewScore;

    @Builder
    public Review(Long reviewSeq, Long productSeq, String userId, String reviewParagraph,
                  Short reviewScore, Long purchaseProductSeq, String purchaseProductOption) {
        this.reviewSeq = reviewSeq;
        this.productSeq = productSeq;
        this.userId = userId;
        this.reviewParagraph = reviewParagraph;
        this.reviewScore = reviewScore;
        this.purchaseProductSeq = purchaseProductSeq;
        this.purchaseProductOption = purchaseProductOption;
    }
}
