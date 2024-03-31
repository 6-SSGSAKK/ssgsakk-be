package com.ssgsakk.ssgdotcom.oauth2;

import com.ssgsakk.ssgdotcom.member.dto.CustomOAuth2User;
import com.ssgsakk.ssgdotcom.point.domain.Point;
import com.ssgsakk.ssgdotcom.point.infrastructure.PointRepository;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final PointRepository pointRepository;

    // 로그인 성공 시, 작동할 핸들러
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String uuid = customUserDetails.getUuid();

        //todo
        // role에 관련된 인가설정이 필요한 경우 아래를 실행
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(uuid, 864000000L);

        // uuid를 통해 포인트 테이블이 없으면 만들어주기
        try {
            pointRepository.save(Point.builder()
                    .point(0)
                    .uuid(uuid)
                    .build());
        } catch (Exception e) {
            // 이미 있는 uuid라서 만들어지지 않음
        }

        response.addHeader("Authorization", "Bearer " + token);

        // 프론트엔드로 response 반환
//        response.sendRedirect("http://localhost:3000/");
        response.sendRedirect("https://www.ssgssak.shop");
    }
}
