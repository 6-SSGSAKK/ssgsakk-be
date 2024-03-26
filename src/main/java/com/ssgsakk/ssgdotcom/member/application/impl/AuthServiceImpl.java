
package com.ssgsakk.ssgdotcom.member.application.impl;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;

import com.ssgsakk.ssgdotcom.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    @Override
    public SignInDto signIn(SignInDto signInDto) {
        // 아이디를 통해 Member 객체 생성
        User member = memberRepository.findByUserId(signInDto.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.FAILED_TO_LOGIN));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 비밀번호 매칭
        if (!(encoder.matches(signInDto.getUserPassword(), member.getPassword()))) {
            throw new BusinessException(ErrorCode.FAILED_TO_LOGIN);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(

                        member.getUsername(),
                        // 입력 비밀번호? 아니면 암호화된 비밀번호?
                        signInDto.getUserPassword()

                )
        );

        // 토큰 값 발행
        String token = "Bearer " + createToken(member);
        log.info("token: {}", token);
        return SignInDto.builder()
                .uuid(member.getUuid())
                .userName(member.getName())
                .token(token)
                .build();
    }

    private String createToken(User member) {
        return jwtUtil.createJwt(member.getUuid(), 60*60*10L);
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

        User member = User.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .name(signUpDto.getUserName())
                .userEmail(signUpDto.getUserEmail())
                .userMobileNum(signUpDto.getUserMobileNum())
                .uuid(signUpDto.getUuid())
                .build();

        // 회원가입 데이터 DB에 저장
        User savedMember = memberRepository.save(member);

        // 저장 여부 확인
        if(savedMember == null) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return SignUpDto.builder()
                .uuid(savedMember.getUuid())
                .userName(savedMember.getName())
                .build();
    }

    @Override
    // 이메일 중복 확인
    public boolean duplicateChecked(String email) {
        return memberRepository.existsByUserEmail(email);
    }

    /**
     * 비밀번호를 바꿔주기 위한 메서드
     */
    public String hashPassword(String userPassword) {
        userPassword = new BCryptPasswordEncoder().encode(userPassword);
        return userPassword;
    }
}
