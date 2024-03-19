package com.ssgsakk.ssgdotcom.category.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long categorySeq;

    @Column(nullable = false)
    private String categoryName;


    @Column(nullable = false)
    private Long level; //카테고리 depth

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategorySeq")
    @JsonBackReference
    private Category parentCategorySeq;


    @OneToMany(mappedBy = "parentCategorySeq",fetch = FetchType.LAZY)
    private List<Category> child = new ArrayList<>(); //하위 카테고리를 가져오기 위한 리스트

    @Builder
    public Category(String categoryName, Long level,Category parentCategorySeq){
        this.categoryName = categoryName;
        this.level = level;
        this.parentCategorySeq = parentCategorySeq;

    }

    public void setCategorySeq(Long categorySeq) {
        this.categorySeq = categorySeq;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public void setParentCategorySeq(Category parentCategorySeq) {
        this.parentCategorySeq = parentCategorySeq;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

}



