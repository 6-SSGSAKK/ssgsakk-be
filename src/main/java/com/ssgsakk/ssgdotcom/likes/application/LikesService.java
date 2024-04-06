package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.AddProductOrCategoryLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductLikesDto;

public interface LikesService {
    void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductLikesDto);

    void deleteProductLikes(DeleteProductLikesDto deleteProductLikesDto);
}
