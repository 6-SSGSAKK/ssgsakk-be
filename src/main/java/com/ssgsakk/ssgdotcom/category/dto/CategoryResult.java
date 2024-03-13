package com.ssgsakk.ssgdotcom.category.dto;


import com.ssgsakk.ssgdotcom.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResult {

    private Long categorySeq;
    private String categoryName;
    private short level;
    private List<CategoryResult> childern;

    public static CategoryResult of(Category category){
        return new CategoryResult(
                category.getCategorySeq(),
                category.getCategoryName(),
                category.getLevel(),
                category.getChildern().stream().map(CategoryResult::of).collect(Collectors.toList())
        );
    }
}
