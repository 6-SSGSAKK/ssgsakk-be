package com.ssgsakk.ssgdotcom.member.infrastructure;

import com.ssgsakk.ssgdotcom.member.domain.OAuth;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.dto.CustomOAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    OAuth findByOauthIdAndOauthType(String oauthId, String oauthType);

    boolean existsByOauthId(String oauthId);

    @Query("SELECT o.user FROM OAuth o WHERE o.oauthId = :oauthId")
    Optional<User> findUserByOauthId(@Param("oauthId") String oauthId);
}
