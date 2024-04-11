package com.ssgsakk.ssgdotcom.category.dto;
import lombok.*;
@Getter
@NoArgsConstructor
public class CategoryDto {

    private String categoryName;
    private Long parentCategorySeq;
    private Integer level;

    @Builder
    public CategoryDto(String categoryName, Long parentCategorySeq, Integer level) {
        this.categoryName = categoryName;
        this.parentCategorySeq = parentCategorySeq;
        this.level = level;
    }
}




