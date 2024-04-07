package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.*;
import com.ssgsakk.ssgdotcom.likes.vo.UserCategoryLikesResponseVo;

import java.util.List;

public interface LikesService {
    void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductLikesDto);

    void deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto deleteProductOrCategoryLikesDto);

    void addFolder(AddLikesFolderDto addLikesFolderDto);

    void deleteFolder(DeleteLikesFolderDto deleteLikesFolderDto);

    void changeFolderName(ChangeLikesFolderDto changeLikesFolderDto);

    List<UserCategoryLikesResponseVo> userCategoryLikes(UserCategoryLikesDto userCategoryLikesDto);
}
