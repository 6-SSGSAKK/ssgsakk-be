package com.ssgsakk.ssgdotcom.likes.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.likes.application.LikesService;
import com.ssgsakk.ssgdotcom.likes.dto.*;
import com.ssgsakk.ssgdotcom.likes.vo.*;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "상품 찜 확인", description = "상품 찜 확인")
    @GetMapping("/check/product-seq/{productSeq}")
    public BaseResponse<CheckProductLikesResponseVo> checkProductLikes(@RequestHeader("Authorization") String accessToken
            , @PathVariable("productSeq") Long productSeq, Authentication authentication) {
        String uuid = getUuid(accessToken);


        CheckProductLikesResponseVo checkProductLikesResponseVo = likesService.checkProductLikes(CheckProductOrCategoryLikesDto.builder()
                .uuid(uuid)
                .productSeq(productSeq)
                .build());

        return new BaseResponse<>("Check Product Likes Success", checkProductLikesResponseVo);
    }

    @Operation(summary = "카테고리 찜 확인", description = "카테고리 찜 확인")
    @GetMapping("/check/category-seq/{categorySeq}")
    public BaseResponse<CheckCategoryLikesResponseVo> checkCategoryLikes(@RequestHeader("Authorization") String accessToken
            , @PathVariable("categorySeq") Long categorySeq) {
        String uuid = getUuid(accessToken);

        CheckCategoryLikesResponseVo checkCategoryLikesResponseVo = likesService.checkCategoryLikes(CheckProductOrCategoryLikesDto.builder()
                .uuid(uuid)
                .categorySeq(categorySeq)
                .build());

        return new BaseResponse<>("Check Category Likes Success", checkCategoryLikesResponseVo);
    }

    @Operation(summary = "상품 또는 카테고리 찜 추가", description = "상품 또는 카테고리 찜 추가")
    @GetMapping("/add")
    public BaseResponse<AddProductOrCategoryLikesResponseVo> addProductLikes(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "product-seq", required = false) Long productSeq
            , @RequestParam(value = "category-seq", required = false) Long categorySeq) {
        String uuid = getUuid(accessToken);

        // 입력받은 product-seq 또는 category-seq 를 추가
        likesService.addProductOrCategoryLikes(AddProductOrCategoryLikesDto.builder()
                .uuid(uuid)
                .productSeq(productSeq)
                .categorySeq(categorySeq)
                .build());

        return new BaseResponse<>("Add Product Or Category Likes Success", AddProductOrCategoryLikesResponseVo.builder()
                .productSeq(productSeq)
                .categorySeq(categorySeq)
                .likeState("like")
                .build());
    }

    @Operation(summary = "상품 또는 카테고리 찜 삭제", description = "상품 또는 카테고리 찜 삭제")
    @GetMapping("/delete")
    public BaseResponse<DeleteProductOrCategoryLikesResponseVo> deleteProductLikes(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "product-seq", required = false) Long productSeq
            , @RequestParam(value = "category-seq", required = false) Long categorySeq) {
        String uuid = getUuid(accessToken);

        likesService.deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto.builder()
                .uuid(uuid)
                .productSeq(productSeq)
                .categorySeq(categorySeq)
                .build());

        return new BaseResponse<>("Delete Product Or Category Likes Success", DeleteProductOrCategoryLikesResponseVo.builder()
                .productSeq(productSeq)
                .categorySeq(categorySeq)
                .likeState("unlike")
                .build());
    }

    @Operation(summary = "전체 찜 폴더 조회", description = "전체 찜 폴더 조회")
    @GetMapping("/user/folder")
    public BaseResponse<List<SelectAllFoldersResponseVo>> selectAllFolders(@RequestHeader("Authorization") String accessToken) {
        String uuid = getUuid(accessToken);

        List<SelectAllFoldersResponseVo> selectAllFoldersResponseVos = likesService.selectAllFolders(SelectAllFoldersDto.builder()
                .uuid(uuid)
                .build());

        return new BaseResponse<>("Select All Folders Success", selectAllFoldersResponseVos);
    }

    @Operation(summary = "찜 폴더 생성", description = "찜 폴더 생성")
    @GetMapping("/folder/add")
    public BaseResponse<Void> addFolder(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "folder-name") String folderName) {
        String uuid = getUuid(accessToken);

        likesService.addFolder(AddLikesFolderDto.builder()
                .uuid(uuid)
                .folderName(folderName)
                .build());

        return new BaseResponse<>("Add Folder Success", null);
    }

    @Operation(summary = "찜 폴더 삭제", description = "찜 폴더 삭제")
    @DeleteMapping("/folder/delete")
    public BaseResponse<Void> deleteFolder(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "folder-seq") Long folderSeq) {
        String uuid = getUuid(accessToken);

        likesService.deleteFolder(DeleteLikesFolderDto.builder()
                .uuid(uuid)
                .folderSeq(folderSeq)
                .build());

        return new BaseResponse<>("Delete Folder Success", null);
    }

    @Operation(summary = "찜 폴더 이름 변경", description = "찜 폴더 이름 변경")
    @PutMapping("/folder/{folderSeq}")
    public BaseResponse<Void> changeFolderName(@RequestHeader("Authorization") String accessToken
            , @PathVariable("folderSeq") Long folderSeq
            , @RequestParam(value = "name-modify") String folderName) {
        String uuid = getUuid(accessToken);

        likesService.changeFolderName(ChangeLikesFolderDto.builder()
                .uuid(uuid)
                .folderSeq(folderSeq)
                .folderName(folderName)
                .build());

        return new BaseResponse<>("Change Folder Name Success", null);
    }

    @Operation(summary = "찜 폴더에 상품 또는 카테고리 추가", description = "찜 폴더에 상품 또는 카테고리 추가")
    @GetMapping("/folder-add/{folderSeq}")
    public BaseResponse<Void> addFolderProductOrCategory(@RequestHeader("Authorization") String accessToken
            , @PathVariable("folderSeq") Long likeFolderSeq
            , @RequestParam(value = "like-product-seq") Long likeProductSeq
            , @RequestParam(value = "like-category-seq") Long likeCategorySeq) {
        String uuid = getUuid(accessToken);

        likesService.addFolderProductOrCategory(AddFolderProductOrCategoryDto.builder()
                .likeFolderSeq(likeFolderSeq)
                .likeProductSeq(likeProductSeq)
                .likeCategorySeq(likeCategorySeq)
                .build());

        return new BaseResponse<>("Add Folder Product Or Category Success", null);
    }

    @Operation(summary = "특정 폴더의 찜 상품 목록 조회", description = "특정 폴더의 찜 상품 목록 조회")
    @GetMapping("/user/product")
    public BaseResponse<List<UserProductLikesResponseVo>> userProductLikes(@RequestHeader("Authorization") String accessToken)
    {
        String uuid = getUuid(accessToken);

        List<UserProductLikesResponseVo> userProductLikesList = likesService.userProductLikes(UserProductLikesDto.builder()
                .uuid(uuid)
                .build());
        return new BaseResponse<>("User Product Likes List", userProductLikesList);
    }


    @Operation(summary = "찜 카테고리 목록 조회", description = "찜 카테고리 목록 조회")
    @GetMapping("/user/category")
    public BaseResponse<List<UserCategoryLikesResponseVo>> userCategoryLikes(@RequestHeader("Authorization") String accessToken)
    {
        String uuid = getUuid(accessToken);

        List<UserCategoryLikesResponseVo> userCategoryLikesList = likesService.userCategoryLikes(UserCategoryLikesDto.builder()
                .uuid(uuid)
                .build());
        return new BaseResponse<>("User Category Likes List", userCategoryLikesList);
    }

    @Operation(summary = "찜 폴더에서 상품 또는 카테고리 삭제", description = "찜 폴더에서 상품 또는 카테고리 삭제")
    @DeleteMapping("/user/folder-delete/{folderSeq}")
    public BaseResponse<Void> userCategoryLikes(@RequestHeader("Authorization") String accessToken
            , @PathVariable("folderSeq") Long folderSeq
            , @RequestParam(value = "product-seq", required = false) Long productSeq
            , @RequestParam(value = "category-seq", required = false) Long categorySeq) {
        String uuid = getUuid(accessToken);


        return new BaseResponse<>("Delete Product Or Category Likes In a Specific Folder Success", null);
    }

    @Operation(summary = "전체 찜에서 상품 삭제", description = "전체 찜에서 상품 삭제")
    @DeleteMapping("/folder-delete/product")
    public BaseResponse<Void> deleteProduct(@RequestHeader("Authorization") String accessToken
            , @RequestBody DeleteLikeProductRequestVo deleteLikeProductRequestVo) {
        String uuid = getUuid(accessToken);
        likesService.deleteProduct(DeleteLikeProductDto.builder()
                .uuid(uuid)
                .likeProductList(deleteLikeProductRequestVo.getLikeProductSeqList())
                .build());

        return new BaseResponse<>("Delete Product from likes Success", null);
    }

    @Operation(summary = "전체 찜에서 카테고리 삭제", description = "전체 찜에서 카테고리 삭제")
    @DeleteMapping("/folder-delete/category")
    public BaseResponse<Void> deleteCategory(@RequestHeader("Authorization") String accessToken
            , @RequestBody DeleteLikeCategoryRequestVo deleteLikeCategoryRequestVo) {
        String uuid = getUuid(accessToken);

        likesService.deleteCategory(DeleteLikeCategoryDto.builder()
                .uuid(uuid)
                .likeCategoryList(deleteLikeCategoryRequestVo.getLikeCategorySeqList())
                .build());

        return new BaseResponse<>("Delete Category from likes Success", null);
    }

    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
