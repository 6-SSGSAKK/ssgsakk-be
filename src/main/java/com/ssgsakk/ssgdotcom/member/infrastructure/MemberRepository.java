//package com.ssgsakk.ssgdotcom.member.infrastructure;
//
//import com.ssgsakk.ssgdotcom.member.domain.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//
//public interface MemberRepository extends JpaRepository<Member, Long> {
//   Optional<Member> findByUserIdAndUserPassword(String userId, String userPassword);
//   Optional<Member> findByUserId(String userId);
//   Optional<Member> findByUuid(String uuid);
//}
