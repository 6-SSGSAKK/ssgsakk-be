package com.ssgsakk.ssgdotcom.category.application;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDTO;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDTO;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDTO categoryDTO){
        log.info("============" + categoryDTO.getParentCategorySeq());
        Category category = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .level(categoryDTO.getLevel())
                .parentCategorySeq(categoryDTO.getParentCategorySeq() == null ? null : categoryRepository.findById(categoryDTO.getParentCategorySeq()).get())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long categorySeq, UpdateCategoryDTO updateCategoryDTO) {
        Category category = categoryRepository.findById(categorySeq)
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다.")); //존재하지 않는 카테고리 값 예외처리
        // 업데이트할 필드들을 설정.
        category.setCategoryName(updateCategoryDTO.getCategoryName());
        category.setLevel(updateCategoryDTO.getLevel());

        if (updateCategoryDTO.getParentCategorySeq() != null) {
            Category parentCategory = categoryRepository.findById(updateCategoryDTO.getParentCategorySeq())
                    .orElseThrow(() -> new NotFoundException("부모 카테고리를 찾을 수 없습니다."));//
            category.setParentCategorySeq(parentCategory);
        } else {
            category.setParentCategorySeq(null); // 부모 카테고리에 값을 넣지 않은 null로 처리함.
        }

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categorySeq){
        Category category = categoryRepository.findById(categorySeq) //카테고리를 찾아옴
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다.")); //없을떄 예외처리
        categoryRepository.delete(category); //찾고 카테고리를 삭제함.
    }

    @Override
    public List<Category> findCategoryParentNull(){   //(대)카테고리조회
        return categoryRepository.findByParentCategorySeq();
    }




}

