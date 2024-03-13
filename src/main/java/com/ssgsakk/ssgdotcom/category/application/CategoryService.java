package com.ssgsakk.ssgdotcom.category.application;

import com.ssgsakk.ssgdotcom.category.dto.CategoryResult;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResult> getCategoryList(){
        List<CategoryResult> results = categoryRepository.findAll().stream().map(CategoryResult::of).collect(Collectors.toList());
        return results;
    }

}
