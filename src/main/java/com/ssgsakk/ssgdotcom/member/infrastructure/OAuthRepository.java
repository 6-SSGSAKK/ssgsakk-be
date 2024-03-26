package com.ssgsakk.ssgdotcom.member.infrastructure;

import com.ssgsakk.ssgdotcom.member.domain.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    OAuth findByOauthIdAndOauthType(String oauthId, String oauthType);
}
