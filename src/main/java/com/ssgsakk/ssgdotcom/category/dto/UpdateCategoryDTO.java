package com.ssgsakk.ssgdotcom.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCategoryDTO {

    private Long categorySeq;
    private String categoryName;
    private Long parentCategorySeq;
    private int level;

    @Builder
    public UpdateCategoryDTO(Long categorySeq, String categoryName, Long parentCategorySeq, int level) {
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
        this.parentCategorySeq = parentCategorySeq;
        this.level = level;
    }
}
