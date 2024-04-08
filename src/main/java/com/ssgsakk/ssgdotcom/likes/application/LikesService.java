package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.*;
import com.ssgsakk.ssgdotcom.likes.vo.CheckProductOrCategoryLikesResponseVo;
import com.ssgsakk.ssgdotcom.likes.vo.UserCategoryLikesResponseVo;
import com.ssgsakk.ssgdotcom.likes.vo.UserProductLikesResponseVo;

import java.util.List;

public interface LikesService {
    void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductLikesDto);

    void deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto deleteProductOrCategoryLikesDto);

    void addFolder(AddLikesFolderDto addLikesFolderDto);

    void deleteFolder(DeleteLikesFolderDto deleteLikesFolderDto);

    void changeFolderName(ChangeLikesFolderDto changeLikesFolderDto);

    List<UserCategoryLikesResponseVo> userCategoryLikes(UserCategoryLikesDto userCategoryLikesDto);

    List<UserProductLikesResponseVo> userProductLikes(UserProductLikesDto userProductLikesDto);

    void addFolderProductOrCategory(AddFolderProductOrCategoryDto addFolderProductOrCategoryDto);

    CheckProductOrCategoryLikesResponseVo checkProductOrCategoryLikes(CheckProductOrCategoryLikesDto checkProductOrCategoryLikesDto);
}
