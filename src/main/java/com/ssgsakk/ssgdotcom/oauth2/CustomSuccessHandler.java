package com.ssgsakk.ssgdotcom.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
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
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;

    // 로그인 성공 시, 작동할 핸들러
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        log.info("customUserDetails >>> {}", customUserDetails);
//        String uuid = customUserDetails.getUuid();
        String oauthId = customUserDetails.getOauthId();
        // uuid는 oauthId로 구한다.
        log.info("oauthId >>> {}", oauthId);

        User user = oAuthRepository.findUserByOauthId(oauthId).orElseThrow(
                () -> new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR)
        );

        String uuid = user.getUuid();
        log.info("findByOauthId >>>>> uuid >>> {}", uuid);


        //todo
        // role에 관련된 인가설정이 필요한 경우 아래를 실행
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(uuid, 864000000L);

//        response.addHeader("Authorization", "Bearer " + token);

        // 기존에 사용하던 소셜 로그인 회원인 경우 확인
        if (oAuthRepository.existsByOauthId(oauthId)) {

            log.info("exist member oauthId: {}", oauthId);
            response.addHeader("Authorization", "Bearer " + token);

            // 프론트엔드로 BaseResponse 반환
            BaseResponse baseResponse = new BaseResponse(">>>>>>>exist member<<<<<", null);
            String baseResponseJson = objectMapper.writeValueAsString(baseResponse);
            response.setContentType("application/json");
            response.getWriter().write(baseResponseJson);
        } else {

            log.info("first member oauthId: {}", oauthId);
            response.addHeader("oauthId>>>>>>", oauthId);



            String baseResponseJson = objectMapper.writeValueAsString(ErrorCode.PASSWORD_SAME_FAILED);
            response.setContentType("application/json");
            response.getWriter().write(baseResponseJson);
        }
    }
}
