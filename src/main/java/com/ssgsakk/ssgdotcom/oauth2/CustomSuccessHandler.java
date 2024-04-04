package com.ssgsakk.ssgdotcom.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.member.domain.OAuth;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.dto.CustomOAuth2User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.member.infrastructure.OAuthRepository;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final OAuthRepository oAuthRepository;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;

    // 로그인 성공 시, 작동할 핸들러
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String oauthId = customUserDetails.getOauthId();

        // 기존 회원
        try {
            User user = oAuthRepository.findUserByOauthId(oauthId).orElseThrow(() -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
            String uuid = user.getUuid();
            String token = jwtUtil.createJwt(uuid, 864000000L);

            OauthResponseVo oauthResponseVo = OauthResponseVo.builder()
                    .token("Bearer " + token)
                    .state(0)
                    .userName(user.getName())
                    .userEmail(customUserDetails.getEmail())
                    .build();

            String result = objectMapper.writeValueAsString(oauthResponseVo);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(result);

            // test
            response.sendRedirect("http://localhost:3000/login/social");
        }
        // oauthId가 없는 사람들 중, user 테이블에 동일 이메일이 있는 지 확인
        catch (BusinessException e) {
            try {
                User user = memberRepository.findByUserEmail(customUserDetails.getEmail()).orElseThrow(() -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
                // 해당 user와 매칭하여 oauth 테이블에 저장
                oAuthRepository.save(OAuth.builder()
                        .user(user)
                        .oauthType("google")
                        .oauthId(oauthId)
                        .build());

                // 토큰 값 반환
                String uuid = user.getUuid();
                String token = jwtUtil.createJwt(uuid, 864000000L);

                OauthResponseVo oauthResponseVo = OauthResponseVo.builder()
                        .token("Bearer " + token)
                        .state(1)
                        .userName(user.getName())
                        .userEmail(customUserDetails.getEmail())
                        .build();

                String result = objectMapper.writeValueAsString(oauthResponseVo);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().print(result);

                // test
                response.sendRedirect("http://localhost:3000/login/social");
            }
            // 동일 이메일이 없는 경우
            catch (BusinessException e1) {
                OauthResponseVo oauthResponseVo = OauthResponseVo.builder()
                        .state(2)
                        .userEmail(customUserDetails.getEmail())
                        .oAuthId(oauthId)
                        .build();

                String result = objectMapper.writeValueAsString(oauthResponseVo);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().print(result);
                // 적절한 HTTP 상태 코드 설정
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // test
                response.sendRedirect("http://localhost:3000/login/social");
            }
        }
    }
}