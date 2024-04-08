package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import com.ssgsakk.ssgdotcom.likes.domain.LikeProduct;
import com.ssgsakk.ssgdotcom.likes.domain.LikedConnect;
import com.ssgsakk.ssgdotcom.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedConnectRepository extends JpaRepository<LikedConnect, Long> {
    List<LikedConnect> findByLikeFolder(LikeFolder folder);
}
