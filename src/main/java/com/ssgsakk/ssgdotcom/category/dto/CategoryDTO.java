package com.ssgsakk.ssgdotcom.category.dto;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String categoryName;
    private Long parentCategorySeq;
    private int level;

}




