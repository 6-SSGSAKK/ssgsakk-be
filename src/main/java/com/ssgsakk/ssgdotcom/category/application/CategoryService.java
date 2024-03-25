package com.ssgsakk.ssgdotcom.category.application;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDTO;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(Long categorySeq, UpdateCategoryDTO updateCategoryDTO);
    void deleteCategory(Long categorySeq);
    List<Category> findCategoryParentNull();



}
