package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.*;

public interface LikesService {
    void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductLikesDto);

    void deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto deleteProductOrCategoryLikesDto);

    void addFolder(AddLikesFolderDto addLikesFolderDto);

    void deleteFolder(DeleteLikesFolderDto deleteLikesFolderDto);

    void changeFolderName(ChangeLikesFolderDto changeLikesFolderDto);
}
