package com.ssgsakk.ssgdotcom.category.presentation;
import com.ssgsakk.ssgdotcom.category.application.CategoryService;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryCustomDto;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDto;
import com.ssgsakk.ssgdotcom.category.vo.CreateCategoryRequestVo;
import com.ssgsakk.ssgdotcom.category.vo.UpdateCategoryRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping() //create category
    public void createcategory(@RequestBody CreateCategoryRequestVo createCategoryRequestVo) {

        categoryService.createCategory(CategoryDto.builder()
                .categoryName(createCategoryRequestVo.getCategoryName())
                .parentCategorySeq(createCategoryRequestVo.getParentCategorySeq())
                .level(createCategoryRequestVo.getLevel())
                .build());

    }

    @PutMapping("update/{categorySeq}") // categorySeq 를 기준으로 수정한다.
    public void updatecategory(@PathVariable Long categorySeq,
                               @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {


        categoryService.updateCategory(UpdateCategoryDto.builder()
                .categorySeq(categorySeq)
                .categoryName(updateCategoryRequestVo.getCategoryName())
                .level(updateCategoryRequestVo.getLevel())
                .parentCategorySeq(updateCategoryRequestVo.getParentCategorySeq())
                .build());
    }

    @DeleteMapping("delete/{categorySeq}") //delete Category
    public void deletecategory(@PathVariable Long categorySeq) {
        categoryService.deleteCategory(categorySeq);
    }

    @GetMapping("/allcategories")  //전체 카테고리 조회
    public ResponseEntity<List<Category>> getcategoryllist() {
        List<Category> categories = categoryService.getCategoryList();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/bigcategories") //대카테고리만 조회
    public ResponseEntity<List<CategoryCustomDto>> getBigCategories() {
        List<CategoryCustomDto> bigCategories = categoryService.getBigCategory();
        return ResponseEntity.ok(bigCategories);
    }
    @GetMapping("/midbybig{parentcategoryid}/") //대분류별 중분류 출력
    public ResponseEntity<List<CategoryCustomDto>> getMiddleCategoryByBig(@PathVariable Long parentCategoryId) {
        List<CategoryCustomDto> middleCategories = categoryService.getMiddleCategoryByBig(parentCategoryId);
        return ResponseEntity.ok(middleCategories);
    }




//    @GetMapping("/midcategories") //중카테고리만 조회
//    public ResponseEntity<List<CategoryCustomDto>> getMidCategories() {
//        List<CategoryCustomDto> midCategories = categoryService.getMidCategory();
//        return ResponseEntity.ok(midCategories);
//    }



//    @GetMapping("/middlecategoriesbybig") //대분류별 중카테고리조회
//    public ResponseEntity<List<Category>> getmiddlecategorybyparent(@RequestParam Long parentCategoryId) {
//        List<Category> categories = categoryService.getMiddleCategoryByParent(parentCategoryId);
//        return ResponseEntity.ok(categories);
//    }

    @GetMapping("/smallcategorybymid{parentcategoryid}") //중분류별 소카테고리조회
    public ResponseEntity<List<Category>> getsmallcategorybyparent(@RequestParam Long parentCategoryId){
        List<Category> categories = categoryService.getSmallCategoryByParent(parentCategoryId);
        return ResponseEntity.ok(categories);
    }




}

