package com.ssgsakk.ssgdotcom.member.application;

import com.ssgsakk.ssgdotcom.member.dto.IdDuplicateCheckDto;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;

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

    // 이메일 중복 확인
    public boolean duplicateChecked(String email);

    // ID 중복 확인
    boolean idDuplicateCheck(IdDuplicateCheckDto idDuplicateCheckDto);

    String findByUserEmail(String uuid);
}
