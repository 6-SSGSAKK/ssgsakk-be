package com.ssgsakk.ssgdotcom.likes.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class LikeProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeProductSeq;

    // 회원과 연결
    @ManyToOne
    private User user;

    // 상품과 연결
    @ManyToOne
    private Product product;

    private int likeState;      // 1이면 좋아요 활성화, 0이면 비활성화

    @Builder
    public LikeProduct(Long likeProductSeq, User user, Product product, int likeState) {
        this.likeProductSeq = likeProductSeq;
        this.user = user;
        this.product = product;
        this.likeState = likeState;
    }
}
