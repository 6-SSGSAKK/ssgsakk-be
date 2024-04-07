package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {

    @Modifying
    @Query("UPDATE LikeProduct lp SET lp.likeState = 0 WHERE lp.user = :user AND lp.product = :product")
    void deleteProductLikes(@Param("user") User user, @Param("product") Product product);

    List<LikeProduct> findByUser(User user);


    Optional<LikeProduct> findByLikeProductSeq(Long likeProductSeq);
}
