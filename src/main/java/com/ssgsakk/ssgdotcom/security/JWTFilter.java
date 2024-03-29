package com.ssgsakk.ssgdotcom.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Getter
public class JWTFilter extends OncePerRequestFilter {

//    public String uuid;
    // JWT 검증을 위해 사용
    private final JWTUtil jwtUtil;
    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        // 헤더가 null이 아니며 Bearer로 시작해야 한다.
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            // 다음 필터로 넘겨준다.
            filterChain.doFilter(request, response);

            return;
        }

        // 토큰에서 username과 role 추출하기 전 순수 token 값만 추출
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        try {
            if (jwtUtil.isExpired(token)) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            jwtExceptionHandler(response, ErrorCode.TOKEN_EXPIRED);
            return;
        }

        // 토큰에서 uuid 추출
        try {
            String uuid = jwtUtil.getUuid(token);
            // 스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(uuid, null);

            // 세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            jwtExceptionHandler(response, ErrorCode.TOKEN_NOT_VALID);
        }
    }

    // 토큰에 대한 오류 발생 시, 커스터마이징해서 Exception 처리
    private void jwtExceptionHandler(HttpServletResponse response, ErrorCode errorCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getDescription());
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class ErrorResponse{
        private final String code;
        private final String message;
    }
}
