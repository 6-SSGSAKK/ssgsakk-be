package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.AddLikesFolderDto;
import com.ssgsakk.ssgdotcom.likes.dto.AddProductOrCategoryLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductOrCategoryLikesDto;

public interface LikesService {
    void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductLikesDto);

    void deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto deleteProductOrCategoryLikesDto);

    void addFolder(AddLikesFolderDto addLikesFolderDto);
}
