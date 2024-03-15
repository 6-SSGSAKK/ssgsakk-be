package com.ssgsakk.ssgdotcom.member.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * [회원]의 컬럼은 다음과 같다.
 *  user_seq, user_id, user_password, user_name, user_email, user_phone_num, user_mobile_num, created_at
 *
 *  컬럼을 전부 엔티티 객체로 제작하며 UserDetails 인터페이스를 구현하여 인증과 권한 부여를 커스터마이징 진행
 *
 *  현재는 관계를 고려하지 않고 진행
 *
 *  [회원] 테이블에는 없지만 uuid, update_at 추가해고 진행
 */

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    // id는 null 불가, 프론트엔드 쪽에서 중복 검사를 진행하므로 unique 설정은 안함
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    // uuid
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    private String userEmail;

    private String userPhoneNum;

    private String userMobileNum;

//    private LocalDateTime createdAt;
//    private LocalDateTime updateAt;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
