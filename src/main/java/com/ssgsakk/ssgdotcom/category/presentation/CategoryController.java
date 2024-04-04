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

    @PostMapping() //카테고리 생성, categoryName,parentCategorySeq,level 생성가능.
    public void createCategory(@RequestBody CreateCategoryRequestVo createCategoryRequestVo) {

        categoryService.createCategory(CategoryDto.builder()
                .categoryName(createCategoryRequestVo.getCategoryName())
                .parentCategorySeq(createCategoryRequestVo.getParentCategorySeq())
                .level(createCategoryRequestVo.getLevel())
                .build());

    }

    @PutMapping("update/{categorySeq}") // categorySeq를 기준으로 수정가능, 수정 가능한 값 categoryName,level,parentCategorySeq
    public void updateCategory(@PathVariable Long categorySeq,
                               @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo) {
        categoryService.updateCategory(UpdateCategoryDto.builder()
                .categorySeq(categorySeq)
                .categoryName(updateCategoryRequestVo.getCategoryName())
                .level(updateCategoryRequestVo.getLevel())
                .parentCategorySeq(updateCategoryRequestVo.getParentCategorySeq())
                .build());
    }

    @DeleteMapping("delete/{categorySeq}") //카테고리삭제
    public void deleteCategory(@PathVariable Long categorySeq) {
        categoryService.deleteCategory(categorySeq);
    }

    @GetMapping("/big-categories") //대카테고리만 조회
    public ResponseEntity<List<CategoryCustomDto>> getBigCategories() {
        List<CategoryCustomDto> bigCategories = categoryService.getBigCategory();
        return ResponseEntity.ok(bigCategories);
    }
    @GetMapping("/mid-by-big") //대카테고리별 중카테고리 조회
    public ResponseEntity<List<CategoryCustomDto>> getMiddleCategoryByBig(@RequestParam Long parentCategoryId) {
        List<CategoryCustomDto> middleCategories = categoryService.getMiddleCategoryByBig(parentCategoryId);
        return ResponseEntity.ok(middleCategories);
    }
    @GetMapping("/small-by-mid") //중카테고리별 소카테고리 조회
    public ResponseEntity<List<CategoryCustomDto>> getSmallCategoryByMid(@RequestParam Long parentCategoryId) {
        List<CategoryCustomDto> smallCategories = categoryService.getSmallCategoryByMid(parentCategoryId);
        return ResponseEntity.ok(smallCategories);
    }
}

