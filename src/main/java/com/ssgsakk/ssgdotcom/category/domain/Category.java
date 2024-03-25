package com.ssgsakk.ssgdotcom.category.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySeq;

    @Column(nullable = false)
    private String categoryName;


    @Column(nullable = false)
    private int level; //카테고리 depth

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategorySeq")
    @JsonBackReference
    private Category parentCategorySeq;


    @OneToMany(mappedBy = "parentCategorySeq",fetch = FetchType.LAZY)
    private List<Category> Child = new ArrayList<>(); //참조된 내역을 저장하는 리스트

    @Builder
    public Category(String categoryName, int level,Category parentCategorySeq){
        this.categoryName = categoryName;
        this.level = level;
        this.parentCategorySeq = parentCategorySeq;

    }

    public void updateCategory(String categoryName, int level, Category parentCategorySeq) {
        this.categoryName = categoryName;
        this.level = level;
        this.parentCategorySeq = parentCategorySeq;
    }


}



