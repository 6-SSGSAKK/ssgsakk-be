package com.ssgsakk.ssgdotcom.likes.domain;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.member.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class LikeCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCategorySeq;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private int categoryState;      // 1이면 좋아요 활성화, 0이면 비활성화

    @Builder
    public LikeCategory(User user, Category category, int categoryState) {
        this.user = user;
        this.category = category;
        this.categoryState = categoryState;
    }
}
