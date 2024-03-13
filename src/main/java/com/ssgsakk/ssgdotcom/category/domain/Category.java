package com.ssgsakk.ssgdotcom.category.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySeq;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategorySeq")
    private Category parentCategorySeq;

    private short level;

    @OneToMany(mappedBy = "parentCategorySeq")
    private List<Category> childern = new ArrayList<>();


}
