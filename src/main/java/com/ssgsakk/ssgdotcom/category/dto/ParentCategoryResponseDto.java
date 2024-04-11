package com.ssgsakk.ssgdotcom.category.dto;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ParentCategoryResponseDto {
    private String categoryName;
    private Long categorySeq;
    private Integer level;

    @Builder
    public ParentCategoryResponseDto(String categoryName, Long categorySeq, Integer level) {
        this.categoryName = categoryName;
        this.categorySeq = categorySeq;
        this.level = level;
    }
}
