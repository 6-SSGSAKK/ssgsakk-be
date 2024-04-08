package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.domain.LikedConnect;
import com.ssgsakk.ssgdotcom.likes.vo.SelectAllFoldersResponseVo;
import com.ssgsakk.ssgdotcom.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LikedConnectRepository extends JpaRepository<LikedConnect, Long> {
    List<LikedConnect> findByLikeFolder(LikeFolder folder);

    Optional<LikedConnect> findByLikeProductAndLikeCategory(LikeProduct likeProduct, LikeCategory likeCategory);

    Collection<? extends LikedConnect> findByLikeProduct(LikeProduct likeProduct);

    @Query("SELECT new com.ssgsakk.ssgdotcom.likes.vo.SelectAllFoldersResponseVo(lc.likeFolder.likeFolderSeq, lc.likeFolder.likeFolderName) FROM LikedConnect lc WHERE lc.likeProduct.user.uuid = :uuid")
    List<SelectAllFoldersResponseVo> selectAllFolders(@Param("uuid") String uuid);
}
