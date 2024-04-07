package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikeFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeFolderRepository extends JpaRepository<LikeFolder, Long> {
}
