package com.ssgsakk.ssgdotcom.likes.domain;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.member.domain.User;
import jakarta.persistence.*;
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

    private int categoryState;
}
