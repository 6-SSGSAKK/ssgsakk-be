package com.ssgsakk.ssgdotcom.likes.application.impl;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.infrastructure.CategoryRepository;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.likes.application.LikesService;
import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.dto.AddProductOrCategoryLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductOrCategoryLikesDto;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikeCategoryRepository;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikeProductRepository;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikesServiceImpl implements LikesService {
    private final LikeProductRepository likeProductRepository;
    private final LikeCategoryRepository likeCategoryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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
        if (addProductOrCategoryLikesDto.getCategorySeq() != null) {
            Category category = categoryRepository.findByCategorySeq(addProductOrCategoryLikesDto.getCategorySeq())
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.CANNOT_FOUND_CATEGORY));

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

            try{
                likeCategoryRepository.deleteCategoryLikes(user, category);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
