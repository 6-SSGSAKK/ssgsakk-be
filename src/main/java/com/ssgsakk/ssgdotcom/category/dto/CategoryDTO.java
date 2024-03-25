package com.ssgsakk.ssgdotcom.category.dto;
import lombok.*;
@Data
@NoArgsConstructor
public class CategoryDTO {

    private String categoryName;
    private Long parentCategorySeq;
    private int level;

    @Builder
    public CategoryDTO(String categoryName, Long parentCategorySeq, int level) {
        this.categoryName = categoryName;
        this.parentCategorySeq = parentCategorySeq;
        this.level = level;
    }
}




