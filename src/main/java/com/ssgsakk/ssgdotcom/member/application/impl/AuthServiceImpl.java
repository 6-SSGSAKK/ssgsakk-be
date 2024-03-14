package com.ssgsakk.ssgdotcom.member.application.impl;

import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.domain.Member;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.error.CustomException;
import com.ssgsakk.ssgdotcom.member.error.ErrorCode;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND_WITH_USER_ID));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Byc >>> " + encoder.matches(signInDto.getUserPassword(), member.getPassword()));

        // 비밀번호 매칭 확인
        if (!(encoder.matches(signInDto.getUserPassword(), member.getPassword()))) {
            new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        return SignInDto.builder()
                .uuid(member.getUuid())
                .userName(member.getUsername())
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

        // member 엔티티를 넘겨도 되나? DTO로 변환해서 넘겨야하나?
        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .userName(signUpDto.getUserName())
                .userEmail(signUpDto.getUserEmail())
                .userPhoneNum(signUpDto.getUserPhoneNum())
                .userMobileNum(signUpDto.getUserMobileNum())
                .uuid(signUpDto.getUuid())
                .createdAt(LocalDateTime.now())
                .build();

        // 저장여부 try-catch
        try {
            Member savedMember = memberRepository.save(member);
//            signUpDto.setDone(true);
            return signUpDto;

        } catch (Exception e) {
            return signUpDto;
        }
    }

    /**
     * 비밀번호를 바꿔주기 위한 메서드
     */
    public String hashPassword(String userPassword) {
        userPassword = new BCryptPasswordEncoder().encode(userPassword);
        return userPassword;
    }
}
