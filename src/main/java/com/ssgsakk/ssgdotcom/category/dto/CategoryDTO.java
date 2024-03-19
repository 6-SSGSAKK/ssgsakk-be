package com.ssgsakk.ssgdotcom.category.dto;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String categoryName;
    private Long parentCategorySeq;
    private Long level;

}




