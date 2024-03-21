package com.ssgsakk.ssgdotcom.member.infrastructure;

import com.ssgsakk.ssgdotcom.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<User, Long> {
   Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
   Optional<User> findByUserId(String userId);
   Optional<User> findByUuid(String uuid);

   // OAuth2
   User findByUserEmail(String userEmail);
}
