package com.ssgsakk.ssgdotcom.category.mapper;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.dto.CategoryDto;
import com.ssgsakk.ssgdotcom.category.vo.CreateCategoryRequestVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper { //카테고리 ModelMapper
    public ModelMapper modelMapper = new ModelMapper();

    public CategoryDto mapToCategoryDto(CreateCategoryRequestVo createCategoryRequestVo){
        return modelMapper.map(createCategoryRequestVo,CategoryDto.class);
    }

    public Category mapToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }

}
