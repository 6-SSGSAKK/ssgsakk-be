package com.ssgsakk.ssgdotcom.category.dto;
import lombok.*;
@Data
@NoArgsConstructor
public class CategoryDto {

    private String categoryName;
    private Long parentCategorySeq;
    private int level;

    @Builder
    public CategoryDto(String categoryName, Long parentCategorySeq, int level) {
        this.categoryName = categoryName;
        this.parentCategorySeq = parentCategorySeq;
        this.level = level;
    }
}




