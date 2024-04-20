package com.ssgsakk.ssgdotcom.likes.domain;

import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class LikedConnect {

    @Id
    @GeneratedValue
    private Long likedConnectSeq;

    @ManyToOne
    private LikeProduct likeProduct;

    @ManyToOne
    private LikeCategory likeCategory;

    @ManyToOne
    private LikeFolder likeFolder;

    @Builder
    public LikedConnect(Long likedConnectSeq, LikeProduct likeProduct, LikeCategory likeCategory, LikeFolder likeFolder) {
        this.likedConnectSeq = likedConnectSeq;
        this.likeProduct = likeProduct;
        this.likeCategory = likeCategory;
        this.likeFolder = likeFolder;
    }
}
