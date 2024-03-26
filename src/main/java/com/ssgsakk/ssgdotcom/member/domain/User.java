package com.ssgsakk.ssgdotcom.member.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 255)
    private String userPassword;

    @Column(nullable = false, length = 255)
    private String uuid;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 255)
    private String userEmail;

    @Column(nullable = true, length = 20)
    private String userMobileNum;

    // 0이면 일반 회원가입 유저, 1이면 소셜 로그인 유저
    @Column(nullable = false, length = 1)
    private int state;

    @Builder
    public User(Long userSeq, String userId, String userPassword, String uuid, String name, String userEmail, String userPhoneNum, String userMobileNum, int state) {
        this.userSeq = userSeq;
        this.userId = userId == null ? " " : userId ;
        this.userPassword = userPassword == null ? " " : userPassword;
        this.uuid = uuid;
        this.name = name == null ? " " : name;
        this.userEmail = userEmail == null ? " " : userEmail;
        this.userMobileNum = userMobileNum;
        this.state = state;
    }


    @OneToMany(mappedBy = "user")
    private List<OAuth> oAuths;

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
