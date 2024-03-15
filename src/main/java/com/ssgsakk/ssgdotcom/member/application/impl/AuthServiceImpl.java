package com.ssgsakk.ssgdotcom.member.application.impl;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.domain.Member;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    @Override
    public SignInDto signIn(SignInDto signInDto) {

        // 아이디를 통해 Member 객체 생성
        // 없으면 바로 에러 코드 보냄
        Member member = memberRepository.findByUserId(signInDto.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.FAILED_TO_LOGIN));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 비밀번호 매칭 확인하세요
        if (!(encoder.matches(signInDto.getUserPassword(), member.getPassword()))) {
            throw new BusinessException(ErrorCode.FAILED_TO_LOGIN);
        }

        return SignInDto.builder()
                .uuid(member.getUuid())
                .userName(member.getName())
                .build();
    }

    @Override
    public SignUpDto signUp(SignUpDto signUpDto) {

        // 비밀번호 암호화
        String hashPassword = hashPassword(signUpDto.getUserPassword());
        signUpDto.setUserPassword(hashPassword);

        // uuid 생성
        UUID uuid = UUID.randomUUID();
        String uuidToStr = uuid.toString();
        signUpDto.setUuid(uuidToStr);

        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .name(signUpDto.getUserName())
                .userEmail(signUpDto.getUserEmail())
                .userPhoneNum(signUpDto.getUserPhoneNum())
                .userMobileNum(signUpDto.getUserMobileNum())
                .uuid(signUpDto.getUuid())
                .build();

        // 회원가입 데이터 DB에 저장
        Member savedMember = memberRepository.save(member);

        // 저장 여부 확인
        if(savedMember == null) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return SignUpDto.builder()
                .uuid(savedMember.getUuid())
                .userName(savedMember.getName())
                .build();
    }

    /**
     * 비밀번호를 바꿔주기 위한 메서드
     */
    public String hashPassword(String userPassword) {
        userPassword = new BCryptPasswordEncoder().encode(userPassword);
        return userPassword;
    }
}
