package com.ssgsakk.ssgdotcom.category.application;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDto;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepositoryImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryImp categoryRepositoryImp;

    @Override
    public void createCategory(CategoryDto categoryDTO) {
        log.info("============" + categoryDTO.getParentCategorySeq());
        Category category = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .level(categoryDTO.getLevel())
                .parentCategorySeq(categoryDTO.getParentCategorySeq() == null ? null : categoryRepository.findById(categoryDTO.getParentCategorySeq()).get())
                .build();

        categoryRepository.save(category);
    }


    @Override
    public void updateCategory(UpdateCategoryDto updateCategoryDTO){
        Category category = categoryRepository.findById(updateCategoryDTO.getCategorySeq())
                .orElseThrow(()->new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Category updatedCategory = Category.builder()
                .categorySeq(category.getCategorySeq()) //바뀌지 않는값은 category에서 꺼내쓰고, 바뀌는값은 DTO에서 꺼내쓰면된다.
                .categoryName(updateCategoryDTO.getCategoryName())
                .level(updateCategoryDTO.getLevel())
                .parentCategorySeq(updateCategoryDTO.getParentCategorySeq() == null ? null : categoryRepository.findById(updateCategoryDTO.getParentCategorySeq()).get())
                .build();

        categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categorySeq) {
        Category category = categoryRepository.findById(categorySeq) //카테고리를 찾아옴
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다.")); //없을떄 예외처리
        categoryRepository.delete(category); //찾고 카테고리를 삭제함.
    }

//    @Override
//    public List<Category> findCategoryParentNull() {   //카테고리전체조회
//        return categoryRepository.findByParentCategorySeq();
//    } //전체카테고리조회
//

    @Override
    public List<Category> getCategoryList(){
       return categoryRepositoryImp.getCategoryList();
    }

}

