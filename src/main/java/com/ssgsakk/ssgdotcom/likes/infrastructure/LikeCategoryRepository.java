package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.vo.UserCategoryLikesResponseVo;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeCategoryRepository extends JpaRepository<LikeCategory, Long> {


    @Modifying
    @Query("UPDATE LikeCategory lc SET lc.categoryState = 0 WHERE lc.user = :user AND lc.category = :category")
    void deleteCategoryLikes(@Param("user") User user, @Param("category") Category category);

    List<LikeCategory> findByUser(User user);

    Optional<LikeCategory> findByLikeCategorySeq(Long likeCategorySeq);

    Optional<LikeCategory> findByUserAndCategory(User user, Category category);

    @Modifying
    @Query("UPDATE LikeCategory lc SET lc.categoryState = :i WHERE lc.user = :user AND lc.category = :category")
    void changeCategoryState(User user, Category category, int i);

    void deleteByLikeCategorySeq(Long likeCategorySeq);
}
