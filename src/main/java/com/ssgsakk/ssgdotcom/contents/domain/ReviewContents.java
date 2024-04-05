
package com.ssgsakk.ssgdotcom.contents.domain;

import com.ssgsakk.ssgdotcom.review.domain.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReviewContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewContentsSeq;

    @ManyToOne
    @JoinColumn(name = "review_seq")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "contents_seq")
    private Contents contents;

    private Integer priority;

    @Builder
    public ReviewContents(Review review, Contents contents, Integer priority){
        this.review = review;
        this.contents = contents;
        this.priority = priority;
    }
}

