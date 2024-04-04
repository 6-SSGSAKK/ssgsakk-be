package com.ssgsakk.ssgdotcom.jjim.domain;

import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class likedConnect {

    @Id
    @GeneratedValue
    private Long likedConnectSeq;

    @ManyToOne
    private LikeProduct likeProduct;

    @ManyToOne
    private LikeCategory likeCategory;

    @ManyToOne
    private LikeFolder likeFolder;
}
