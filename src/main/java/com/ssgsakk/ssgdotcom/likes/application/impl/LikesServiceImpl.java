package com.ssgsakk.ssgdotcom.likes.application.impl;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ContentsRepository;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ProductContentsRepository;
import com.ssgsakk.ssgdotcom.likes.application.LikesService;
import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.domain.LikedConnect;
import com.ssgsakk.ssgdotcom.likes.dto.*;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikeCategoryRepository;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikeFolderRepository;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikeProductRepository;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikedConnectRepository;
import com.ssgsakk.ssgdotcom.likes.vo.*;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikesServiceImpl implements LikesService {
    private final LikeProductRepository likeProductRepository;
    private final LikeCategoryRepository likeCategoryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final LikeFolderRepository likeFolderRepository;
    private final ProductContentsRepository productContentsRepository;
    private final LikedConnectRepository likedConnectRepository;
    private final ContentsRepository contentsRepository;


    @Override
    @Transactional
    public void addProductOrCategoryLikes(AddProductOrCategoryLikesDto addProductOrCategoryLikesDto) {
        // uuid를 가지고 User 엔티티 획득
        User user = memberRepository.findByUuid(addProductOrCategoryLikesDto.getUuid()).orElseThrow(
                () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));

        // Product 추가한 경우
        if (addProductOrCategoryLikesDto.getProductSeq() != null) {

            Product product = productRepository.findByProductSeq(addProductOrCategoryLikesDto.getProductSeq())
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));


            // likeProduct 테이블에 이미 있는 지 확인
            LikeProduct likeProduct = likeProductRepository.findByUserAndProduct(user, product).orElse(null);

            // 이미 존재하는 경우
            if (likeProduct != null) {
                // likeProduct의 likeState 상태만 1로 변경해준다.
                try {
                    likeProductRepository.changeLikeState(user, product, 1);
                } catch (Exception e) {
                    throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
            // 최초 찜 하는 경우
            else {
                try {
                    likeProductRepository.save(LikeProduct.builder()
                            .user(user)
                            .product(product)
                            .likeState(1)
                            .build());
                } catch (Exception e) {
                    throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
        }
        if (addProductOrCategoryLikesDto.getCategorySeq() != null) {
            Category category = categoryRepository.findByCategorySeq(addProductOrCategoryLikesDto.getCategorySeq())
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));

            // likeCategory 테이블에 이미 있는 지 확인
            LikeCategory likeCategory = likeCategoryRepository.findByUserAndCategory(user, category).orElse(null);

            // 이미 존재하는 경우
            if (likeCategory != null) {
                // likeCategory의 categoryState 상태만 1로 변경
                try {
                    likeCategoryRepository.changeCategoryState(user, category, 1);
                } catch (Exception e) {
                    throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
            // 최초 찜 하는 경우
            else {
                try {
                    likeCategoryRepository.save(LikeCategory.builder()
                            .user(user)
                            .category(category)
                            .categoryState(1)
                            .build());
                } catch (Exception e) {
                    throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @Override
    @Transactional
    public void deleteProductOrCategoryLikes(DeleteProductOrCategoryLikesDto deleteProductOrCategoryLikesDto) {

        User user = memberRepository.findByUuid(deleteProductOrCategoryLikesDto.getUuid()).orElseThrow(
                () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));

        if (deleteProductOrCategoryLikesDto.getProductSeq() != null) {
            Product product = productRepository.findByProductSeq(deleteProductOrCategoryLikesDto.getProductSeq())
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));

            try {
                likeProductRepository.deleteProductLikes(user, product);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
        if (deleteProductOrCategoryLikesDto.getCategorySeq() != null) {
            Category category = categoryRepository.findByCategorySeq(deleteProductOrCategoryLikesDto.getCategorySeq())
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));

            try {
                likeCategoryRepository.deleteCategoryLikes(user, category);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    @Transactional
    public void addFolder(AddLikesFolderDto addLikesFolderDto) {
        try {
            likeFolderRepository.save(LikeFolder.builder()
                    .likeFolderName(addLikesFolderDto.getFolderName())
                    .uuid(addLikesFolderDto.getUuid())
                    .build());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteFolder(DeleteLikesFolderDto deleteLikesFolderDto) {
        try {
            likeFolderRepository.deleteByLikeFolderSeq(deleteLikesFolderDto.getFolderSeq());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void changeFolderName(ChangeLikesFolderDto changeLikesFolderDto) {
        try {
            likeFolderRepository.changeFolderName(changeLikesFolderDto.getFolderSeq(), changeLikesFolderDto.getFolderName());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<UserCategoryLikesResponseVo> userCategoryLikes(UserCategoryLikesDto userCategoryLikesDto) {
        User user = memberRepository.findByUuid(userCategoryLikesDto.getUuid()).orElseThrow(
                () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
        try {
            // 전체 조회
            if (userCategoryLikesDto.getFolderSeq() == null) {
                List<LikeCategory> likeCategories = likeCategoryRepository.findByUser(user);
                List<UserCategoryLikesResponseVo> responseVos = new ArrayList<>();
                for (LikeCategory likeCategory : likeCategories) {
                    Category category = likeCategory.getCategory();
                    UserCategoryLikesResponseVo categoryLikesResponseVo = UserCategoryLikesResponseVo.builder()
                            .categorySeq(category.getCategorySeq())
                            .categoryName(category.getCategoryName())
                            .build();
                    responseVos.add(categoryLikesResponseVo);
                }
                return responseVos;
            }

            // 특정 폴더 카테고리 조회
            LikeFolder folder = likeFolderRepository.findByLikeFolderSeq(userCategoryLikesDto.getFolderSeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_FOLDER));

            List<LikedConnect> likedConnects = likedConnectRepository.findByLikeFolder(folder);
            List<LikeCategory> categories = new ArrayList<>();
            for (LikedConnect likedConnect : likedConnects) {
                categories.add(likedConnect.getLikeCategory());
            }

            List<UserCategoryLikesResponseVo> responseVos = new ArrayList<>();
            for (LikeCategory likeCategory : categories) {
                Category category = likeCategory.getCategory();
                UserCategoryLikesResponseVo categoryLikesResponseVo = UserCategoryLikesResponseVo.builder()
                        .categorySeq(category.getCategorySeq())
                        .categoryName(category.getCategoryName())
                        .build();
                responseVos.add(categoryLikesResponseVo);
            }
            return responseVos;

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<UserProductLikesResponseVo> userProductLikes(UserProductLikesDto userProductLikesDto) {
        try {
            User user = memberRepository.findByUuid(userProductLikesDto.getUuid()).orElseThrow(
                    () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));

            // 전체 조회
            if (userProductLikesDto.getFolderSeq() == null) {
                List<LikeProduct> likeProducts = likeProductRepository.findByUser(user);
                List<UserProductLikesResponseVo> responseVos = new ArrayList<>();
                for (LikeProduct likeProduct : likeProducts) {
                    Product product = likeProduct.getProduct();
                    ProductContents productContents = productContentsRepository.findByProduct(product).orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));


                    if (productContents.getPriority() == 1) {
                        Contents contents = productContents.getContents();
                    }
                    List<ContentsUrl> contentsUrls = new ArrayList<>();
                    ContentsUrl contentsUrl = ContentsUrl.builder()
                            .priority(productContents.getPriority())
                            .contentUrl(productContents.getContents().getContentUrl())
                            .contentDescription(productContents.getContents().getContentDescription())
                            .build();
                    contentsUrls.add(contentsUrl);

                    responseVos.add(UserProductLikesResponseVo.builder()
                            .likeProductSeq(likeProduct.getLikeProductSeq())
                            .productSeq(product.getProductSeq())
                            .productName(product.getProductName())
                            .productPrice(product.getProductPrice())
                            .discountPercent(product.getDiscountPercent())
                            .vendor(product.getVendor().getVendorName())
                            .deliveryType(product.getDeliveryType().name())
                            .contents(contentsUrls)
                            .build());
                }
                return responseVos;
            }
            // 특정 folder 조회
            else {
                LikeFolder folder = likeFolderRepository.findByLikeFolderSeq(userProductLikesDto.getFolderSeq()).orElseThrow(
                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_FOLDER));

                List<UserProductLikesResponseVo> responseVos = new ArrayList<>();
                List<LikedConnect> likedConnects = likedConnectRepository.findByLikeFolder(folder);
                for (LikedConnect likedConnect : likedConnects) {
                    Product product = likedConnect.getLikeProduct().getProduct();
                    ProductContents productContents = productContentsRepository.findByProduct(product).orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
                    Contents contents = productContents.getContents();

                    List<ContentsUrl> contentsUrls = new ArrayList<>();
                    ContentsUrl contentsUrl = ContentsUrl.builder()
                            .priority(productContents.getPriority())
                            .contentUrl(productContents.getContents().getContentUrl())
                            .contentDescription(productContents.getContents().getContentDescription())
                            .build();
                    contentsUrls.add(contentsUrl);

                    responseVos.add(UserProductLikesResponseVo.builder()
                            .likeProductSeq(likedConnect.getLikeProduct().getLikeProductSeq())
                            .productSeq(product.getProductSeq())
                            .productName(product.getProductName())
                            .productPrice(product.getProductPrice())
                            .discountPercent(product.getDiscountPercent())
                            .vendor(product.getVendor().getVendorName())
                            .deliveryType(product.getDeliveryType().name())
                            .contents(contentsUrls)
                            .build());
                }
                return responseVos;
            }

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }


//        try {
//            // 전체 조회
//            if (userProductLikesDto.getFolderSeq() == null) {
//                User user = memberRepository.findByUuid(userProductLikesDto.getUuid()).orElseThrow(
//                        () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
//
//                List<LikeProduct> products = likeProductRepository.findByUser(user);
//                List<UserProductLikesResponseVo> responseVos = new ArrayList<>();
//
//                for (LikeProduct likeProduct : products) {
//                    Product product = likeProduct.getProduct();
//                    List<ContentsUrl> contentsUrls = new ArrayList<>();
//
//                    // ProductContents 획득
//                    ProductContents productContents = productContentsRepository.findByProductAndPriority(product, 1).orElseThrow(
//                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
//
//                    ContentsUrl contentsUrl = ContentsUrl.builder()
//                            .priority(productContents.getPriority())
//                            .contentUrl(productContents.getContents().getContentUrl())
//                            .contentDescription(productContents.getContents().getContentDescription())
//                            .build();
//
//                    contentsUrls.add(contentsUrl);
//
//                    UserProductLikesResponseVo productLikesResponseVo = UserProductLikesResponseVo.builder()
//                            .productName(product.getProductName())
//                            .productPrice(product.getProductPrice())
//                            .discountPercent(product.getDiscountPercent())
//                            .vendor(product.getVendor().getVendorName())
//                            .deliveryType(product.getDeliveryType().name())
//                            .contents(contentsUrls)
//                            .build();
//                    responseVos.add(productLikesResponseVo);
//                }
//                return responseVos;
//            }
//
//            // 특정 폴더 상품 조회
//            LikeFolder folder = likeFolderRepository.findByLikeFolderSeq(userProductLikesDto.getFolderSeq()).orElseThrow(
//                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_FOLDER));
//
//            List<LikedConnect> likedConnects = likedConnectRepository.findByLikeFolder(folder);
//            List<LikeProduct> products = new ArrayList<>();
//            for (LikedConnect likedConnect : likedConnects) {
//                products.add(likedConnect.getLikeProduct());
//            }
//
//            List<UserProductLikesResponseVo> responseVos = new ArrayList<>();
//
//            for (LikeProduct likeProduct : products) {
//                Product product = likeProduct.getProduct();
//                List<ContentsUrl> contentsUrls = new ArrayList<>();
//
//                // ProductContents 획득
//                ProductContents productContents = productContentsRepository.findByProductAndPriority(product, 1).orElseThrow(
//                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
//
//                ContentsUrl contentsUrl = ContentsUrl.builder()
//                        .priority(productContents.getPriority())
//                        .contentUrl(productContents.getContents().getContentUrl())
//                        .contentDescription(productContents.getContents().getContentDescription())
//                        .build();
//
//                contentsUrls.add(contentsUrl);
//
//                UserProductLikesResponseVo productLikesResponseVo = UserProductLikesResponseVo.builder()
//                        .productName(product.getProductName())
//                        .productPrice(product.getProductPrice())
//                        .discountPercent(product.getDiscountPercent())
//                        .vendor(product.getVendor().getVendorName())
//                        .deliveryType(product.getDeliveryType().name())
//                        .contents(contentsUrls)
//                        .build();
//                responseVos.add(productLikesResponseVo);
//            }
//            return responseVos;
//
//        } catch (Exception e) {
//            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
//        }
    }

    @Override
    @Transactional
    public void addFolderProductOrCategory(AddFolderProductOrCategoryDto addFolderProductOrCategoryDto) {
        try {
            LikeProduct likeProduct = likeProductRepository.findByLikeProductSeq(addFolderProductOrCategoryDto.getLikeProductSeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
            LikeCategory likeCategory = likeCategoryRepository.findByLikeCategorySeq(addFolderProductOrCategoryDto.getLikeCategorySeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));
            LikeFolder likeFolder = likeFolderRepository.findByLikeFolderSeq(addFolderProductOrCategoryDto.getLikeFolderSeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_FOLDER));

            likedConnectRepository.save(LikedConnect.builder()
                    .likeProduct(likeProduct)
                    .likeCategory(likeCategory)
                    .likeFolder(likeFolder)
                    .build());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<SelectAllFoldersResponseVo> selectAllFolders(SelectAllFoldersDto selectAllFoldersDto) {

        try {
            List<SelectAllFoldersResponseVo> selectAllFoldersResponseVos = new ArrayList<>();

            List<LikeFolder> likeFolders = likeFolderRepository.findByUuid(selectAllFoldersDto.getUuid());
            for (LikeFolder likeFolder : likeFolders) {
                selectAllFoldersResponseVos.add(SelectAllFoldersResponseVo.builder()
                        .likeFolderSeq(likeFolder.getLikeFolderSeq())
                        .likeFolderName(likeFolder.getLikeFolderName())
                        .build());
            }
            return selectAllFoldersResponseVos;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public CheckProductLikesResponseVo checkProductLikes(CheckProductOrCategoryLikesDto checkProductOrCategoryLikesDto) {
        try {
            User user = memberRepository.findByUuid(checkProductOrCategoryLikesDto.getUuid()).orElseThrow(
                    () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
            Product product = productRepository.findByProductSeq(checkProductOrCategoryLikesDto.getProductSeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));

            LikeProduct likeProduct = likeProductRepository.findByUserAndProduct(user, product).orElse(null);

            // likeProduct가 있는 경우
            if (likeProduct != null) {
                return CheckProductLikesResponseVo.builder()
                        .productSeq(checkProductOrCategoryLikesDto.getProductSeq())
                        .likeState(likeProduct.getLikeState())
                        .build();
            } else {
                return CheckProductLikesResponseVo.builder()
                        .productSeq(checkProductOrCategoryLikesDto.getProductSeq())
                        .likeState(0)
                        .build();
            }

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public CheckCategoryLikesResponseVo checkCategoryLikes(CheckProductOrCategoryLikesDto checkProductOrCategoryLikesDto) {
        try {
            User user = memberRepository.findByUuid(checkProductOrCategoryLikesDto.getUuid()).orElseThrow(
                    () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
            Category category = categoryRepository.findByCategorySeq(checkProductOrCategoryLikesDto.getCategorySeq()).orElseThrow(
                    () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));

            LikeCategory likeCategory = likeCategoryRepository.findByUserAndCategory(user, category).orElse(null);

            // likeCategory가 없는 경우
            if (likeCategory != null) {
                return CheckCategoryLikesResponseVo.builder()
                        .categorySeq(checkProductOrCategoryLikesDto.getCategorySeq())
                        .likeState(likeCategory.getCategoryState())
                        .build();
            } else {
                return CheckCategoryLikesResponseVo.builder()
                        .categorySeq(checkProductOrCategoryLikesDto.getCategorySeq())
                        .likeState(0)
                        .build();
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public void deleteProduct(DeleteLikeProductDto deleteLikeProductDto) {
        try {
            for (Long likeProductSeq : deleteLikeProductDto.getLikeProductList()) {
                LikeProduct likeProduct = likeProductRepository.findByLikeProductSeq(likeProductSeq).orElseThrow(
                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));
//                likedConnectRepository.deleteByLikeProduct(likeProduct);      // 폴더 폐기했기 때문에 connect 테이블은 필요가 없음.
                likeProductRepository.deleteByLikeProductSeq(likeProductSeq);
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(DeleteLikeCategoryDto deleteLikeCategoryDto) {
        try {
            for (Long likeCategorySeq : deleteLikeCategoryDto.getLikeCategoryList()) {
                LikeCategory likeCategory = likeCategoryRepository.findByLikeCategorySeq(likeCategorySeq).orElseThrow(
                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));
                likeCategoryRepository.deleteByLikeCategorySeq(likeCategorySeq);
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}