package com.ssgsakk.ssgdotcom.category.application;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.domain.QCategory;
import com.ssgsakk.ssgdotcom.category.dto.CategoryCustomDto;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDto;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepositoryImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryImp categoryRepositoryImp;

    private CategoryCustomDto mapTupleToDTO(com.querydsl.core.Tuple tuple) { //카테고리 튜플 DTO로 변환
        Long categorySeq = tuple.get(QCategory.category.categorySeq);
        String categoryName = tuple.get(QCategory.category.categoryName);
        Integer level = tuple.get(QCategory.category.level);
        return new CategoryCustomDto(categoryName, categorySeq, level);
    }

    @Override
    public void createCategory(CategoryDto categoryDTO) { //카테고리생성
        log.info("============" + categoryDTO.getParentCategorySeq());
        Category category = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .level(categoryDTO.getLevel())
                .parentCategorySeq(categoryDTO.getParentCategorySeq() == null ? null : categoryRepository.
                        findById(categoryDTO.getParentCategorySeq()).get())
                .build();

        categoryRepository.save(category);
    }


    @Override
    public void updateCategory(UpdateCategoryDto updateCategoryDTO){ //카테고리수정
        Category category = categoryRepository.findById(updateCategoryDTO.getCategorySeq())
                .orElseThrow(()->new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Category updatedCategory = Category.builder()
                .categorySeq(category.getCategorySeq()) //바뀌지 않는값은 category에서 꺼내쓰고, 바뀌는값은 DTO에서 꺼내쓰면된다.
                .categoryName(updateCategoryDTO.getCategoryName())
                .level(updateCategoryDTO.getLevel())
                .parentCategorySeq(updateCategoryDTO.getParentCategorySeq() == null ? null : categoryRepository.
                        findById(updateCategoryDTO.getParentCategorySeq()).get())
                .build();

        categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categorySeq) { //카테고리 삭제
        Category category = categoryRepository.findById(categorySeq) //카테고리를 찾아옴
                .orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다.")); //없을떄 예외처리
        categoryRepository.delete(category); //찾고 카테고리를 삭제함.
    }

    @Override
    public List<Category> getCategoryList(){

        return categoryRepositoryImp.getCategoryList(); //전체카테고리조회
    }

    @Override
    public List<CategoryCustomDto> getBigCategory() { //대카테고리조회
        List<com.querydsl.core.Tuple> tuples = categoryRepositoryImp.getBigCategory();
        List<CategoryCustomDto> customDto = tuples.stream()
                .map(this::mapTupleToDTO)
                .collect(Collectors.toList());
        return customDto;
    }

//    @Override
//    public List<CategoryCustomDto> getMidCategoryByBig(Long parentCategoryId){ //대카테고리별 중카테고리조회
//        List<com.querydsl.core.Tuple> tuples = categoryRepositoryImp.getMiddleCategoryByBig(Long parentCategoryId);
//        List<CategoryCustomDto> customdto = tuples.stream()
//                .map(this::mapTupleToDTO)
//                .collect(Collectors.toList());
//        return customdto;
//    }

    @Override
    public List<CategoryCustomDto> getMiddleCategoryByBig(Long parentCategoryId) { //대카테고리별 중 카테고리 조회
        List<com.querydsl.core.Tuple> tuples = categoryRepositoryImp.getMiddleCategoryByBig(parentCategoryId);
        List<CategoryCustomDto> customDto = tuples.stream()
                .map(this::mapTupleToDTO)
                .collect(Collectors.toList());
        return customDto;
    }


    @Override
    public List<Category> getSmallCategoryByParent(Long parentCategoryId){ //중카테고리별 소카테고리조회
        return categoryRepositoryImp.getSamllCategoryByMid(parentCategoryId);
    }




//    @Override
//    public List<CategoryCustomDto> getMidCategory() { //중카테고리조회
//        List<com.querydsl.core.Tuple> tuples = categoryRepositoryImp.getMidCategory();
//        List<CategoryCustomDto> collect = tuples.stream()
//                .map(this::mapTupleToDTO)
//                .collect(Collectors.toList());
//        return collect;
//    }

//    @Override
//    public List<Category> getMiddleCategoryByParent(Long parentCategoryId){
//
//        return categoryRepositoryImp.getMiddleCategoryByParent(parentCategoryId); // 대카테고리별 중카테고리 조회
//    }




}

