package com.ssgsakk.ssgdotcom.category.presentation;
import com.ssgsakk.ssgdotcom.category.application.CategoryService;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDTO;
import com.ssgsakk.ssgdotcom.category.dto.UpdateCategoryDTO;
import com.ssgsakk.ssgdotcom.category.vo.UpdateCategoryRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping() //create category
    public void createcategory(@RequestBody CategoryDTO category){
        categoryService.createCategory(category);

    }

    @PutMapping("update/{categorySeq}") // categorySeq 를 기준으로 수정한다.
    public void updatecategory(@PathVariable Long categorySeq,
                               @RequestBody UpdateCategoryRequestVo updateCategoryRequestVo){


        categoryService.updateCategory(UpdateCategoryDTO.builder()
                .categorySeq(categorySeq)
                .categoryName(updateCategoryRequestVo.getCategoryName())
                .level(updateCategoryRequestVo.getLevel())
                .parentCategorySeq(updateCategoryRequestVo.getParentCategorySeq())
                .build());
    }

    @DeleteMapping("delete/{categorySeq}") //delete Category Controller
    public void deletecategory(@PathVariable Long categorySeq){
        categoryService.deleteCategory(categorySeq);
    }

    @GetMapping("/allcategories")  //전체 카테고리 조회
    public ResponseEntity<List<Category>> findcategoryparentnull(){
        List<Category> categories = categoryService.findCategoryParentNull();
        return ResponseEntity.ok(categories);
    }



}

