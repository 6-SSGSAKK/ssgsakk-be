package com.ssgsakk.ssgdotcom.category.application;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryCustomDto;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDTO);
    void updateCategory(UpdateCategoryDto updateCategoryDTO);
    void deleteCategory(Long categorySeq);
    List<Category> getCategoryList(); //전체카테고리조회
    List<CategoryCustomDto> getBigCategory(); //대카테고리조회
    List<CategoryCustomDto> getMiddleCategoryByBig(Long parentCategoryId);  // 대카테고리별 중카테고리조회
    List<Category> getSmallCategoryByParent(Long parentCategoryId); //중 카테고리별 소 카테고리조회
}
