package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.ssgsakk.ssgdotcom.likes.vo.UserProductLikesResponseVo;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductSeq(Long productSeq);

//    @Query("SELECT new com.ssgsakk.ssgdotcom.likes.vo.UserProductLikesResponseVo()")
//    List<UserProductLikesResponseVo> userAllProductLikes(String uuid);
}


//@Query("SELECT new com.ssgsakk.ssgdotcom.likes.vo.SelectAllFoldersResponseVo(lc.likeFolder.likeFolderSeq, lc.likeFolder.likeFolderName) FROM LikedConnect lc WHERE lc.likeProduct.user.uuid = :uuid")
