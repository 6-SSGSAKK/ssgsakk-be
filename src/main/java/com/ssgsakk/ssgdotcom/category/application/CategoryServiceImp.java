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
    public void createCategory(CategoryDTO categoryDTO) {
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
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        // 업데이트 메서드를 사용하여 필드를 설정
        category.updateCategory(
                updateCategoryDTO.getCategoryName(),
                updateCategoryDTO.getLevel(),
                updateCategoryDTO.getParentCategorySeq()
                        == null ? null : categoryRepository
                        .findById(updateCategoryDTO.getParentCategorySeq()).orElse(null)
        );

        categoryRepository.save(category);
    }
    @Override
    public void deleteCategory(Long categorySeq) {
        Category category = categoryRepository.findById(categorySeq) //카테고리를 찾아옴
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다.")); //없을떄 예외처리
        categoryRepository.delete(category); //찾고 카테고리를 삭제함.
    }

    @Override
    public List<Category> findCategoryParentNull() {   //카테고리전체조회
        return categoryRepository.findByParentCategorySeq();
    } //전체카테고리조회
}

