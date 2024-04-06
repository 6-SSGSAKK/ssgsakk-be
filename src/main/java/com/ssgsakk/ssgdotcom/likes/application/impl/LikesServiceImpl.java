package com.ssgsakk.ssgdotcom.likes.application.impl;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.likes.application.LikesService;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.dto.AddProductLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductLikesDto;
import com.ssgsakk.ssgdotcom.likes.infrastructure.LikesRepository;
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
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public void addProductLikes(AddProductLikesDto addProductLikesDto) {
        // uuid를 가지고 User 엔티티 획득
        User user = memberRepository.findByUuid(addProductLikesDto.getUuid()).orElseThrow(
                () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));

        // User 엔티티를 가지고 LikeProduct 엔티티 빌드
        Product product = productRepository.findByProductSeq(addProductLikesDto.getProductSeq())
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));

        try {
            likesRepository.save(LikeProduct.builder()
                    .user(user)
                    .product(product)
                    .likeState(1)
                    .build());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteProductLikes(DeleteProductLikesDto deleteProductLikesDto) {

        User user = memberRepository.findByUuid(deleteProductLikesDto.getUuid()).orElseThrow(
                () -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));

        Product product = productRepository.findByProductSeq(deleteProductLikesDto.getProductSeq())
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));

        try {
            likesRepository.deleteProductLikes(user, product);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
