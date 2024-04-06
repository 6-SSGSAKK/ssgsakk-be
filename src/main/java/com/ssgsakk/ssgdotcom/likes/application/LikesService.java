package com.ssgsakk.ssgdotcom.likes.application;

import com.ssgsakk.ssgdotcom.likes.dto.AddProductLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductLikesDto;

public interface LikesService {
    void addProductLikes(AddProductLikesDto addProductLikesDto);

    void deleteProductLikes(DeleteProductLikesDto deleteProductLikesDto);
}
