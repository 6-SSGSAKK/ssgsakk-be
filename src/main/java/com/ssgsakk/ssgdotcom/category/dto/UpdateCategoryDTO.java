package com.ssgsakk.ssgdotcom.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryDTO {

    private String categoryName;
    private Long parentCategorySeq;
    private int level;

}
