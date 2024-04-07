package com.ssgsakk.ssgdotcom.likes.infrastructure;

import com.ssgsakk.ssgdotcom.likes.domain.LikedConnect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedConnectRepository extends JpaRepository<LikedConnect, Long> {
}
