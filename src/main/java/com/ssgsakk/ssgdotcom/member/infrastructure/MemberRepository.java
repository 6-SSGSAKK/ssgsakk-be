package com.ssgsakk.ssgdotcom.member.infrastructure;

import com.ssgsakk.ssgdotcom.member.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<User, Long> {
   Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
   Optional<User> findByUserId(String userId);
   Optional<User> findByUuid(String uuid);

   // OAuth2
   User findByUserEmail(String userEmail);
   @Query("SELECT u.userSeq FROM User u WHERE u.userEmail = :userEmail")
   long findByEmail(String userEmail);

   @Transactional
   @Modifying
   @Query("UPDATE User u SET u.state = :stateValue WHERE userEmail = :email")
   void updateState(@Param("stateValue") int stateValue, @Param("email") String email);
}
