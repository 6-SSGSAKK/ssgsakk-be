package com.ssgsakk.ssgdotcom.category.application;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDTO);
    void updateCategory(UpdateCategoryDto updateCategoryDTO);
    void deleteCategory(Long categorySeq);
    List<Category> findCategoryParentNull();




}
