package com.ssgsakk.ssgdotcom.member.application.impl;

import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.domain.Member;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.member.vo.SignInRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    @Override
    public SignInDto signIn(SignInDto signInDto) {

        Member member = memberRepository.findByUserIdAndUserPassword(signInDto.getUserId(), signInDto.getUserPassword());

        signInDto = SignInDto.builder()
                .userName(member.getUsername())
                .uuid(member.getUuid())
                .build();

        return signInDto;
    }

    @Override
    public SignUpDto signUp(SignUpDto signUpDto) {
        Member member;

        return null;
    }

    /**
     * 비밀번호를 바꿔주기 위한 메서드
     */
    public String hashPassword(String userPassword) {
        userPassword = new BCryptPasswordEncoder().encode(userPassword);
        return userPassword;
    }
}
