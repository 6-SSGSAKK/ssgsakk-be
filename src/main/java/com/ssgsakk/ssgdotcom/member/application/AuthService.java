package com.ssgsakk.ssgdotcom.member.application;

import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;
import com.ssgsakk.ssgdotcom.member.vo.SignInRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpRequestVo;

/**
 * Auth 관련 서비스
 *  회원가입과 로그인 메서드
 *
 *  입력은 RequestVo 사용
 *  출력은 ResponseVo 사용
 */
public interface AuthService {
    SignInDto signIn(SignInDto signInDto);
    SignUpDto signUp(SignUpDto signUpDto);
}
