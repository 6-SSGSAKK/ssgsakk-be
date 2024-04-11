package com.ssgsakk.ssgdotcom.category.dto;
import lombok.*;

@Getter
@NoArgsConstructor //카테고리 조회 DTO 튜플의 값을 DTO로 변환해서 서비스에 전달
public class CategoryCustomDto {

    private String categoryName;
    private Long CategorySeq;
    private Integer level;

    @Builder
    public CategoryCustomDto(String categoryName, Long CategorySeq, Integer level) {
        this.categoryName = categoryName;
        this.CategorySeq = CategorySeq;
        this.level = level;
    }
}
