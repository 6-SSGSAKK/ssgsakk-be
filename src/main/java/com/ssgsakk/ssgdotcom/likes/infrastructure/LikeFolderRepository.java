package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeCategory;
import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeFolderRepository extends JpaRepository<LikeFolder, Long> {
    void deleteByLikeFolderSeq(Long likeFolderSeq);

    @Modifying
    @Query("UPDATE LikeFolder lf SET lf.likeFolderName = :folderName WHERE lf.likeFolderSeq = :folderSeq")
    void changeFolderName(@Param("folderSeq") Long folderSeq,@Param("folderName") String folderName);

    Optional<LikeFolder> findByLikeFolderSeq(Long likeFolderSeq);
}
