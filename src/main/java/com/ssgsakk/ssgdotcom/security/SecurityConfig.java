package com.ssgsakk.ssgdotcom.security;

import com.ssgsakk.ssgdotcom.member.application.CustomOAuth2UserService;
import com.ssgsakk.ssgdotcom.oauth2.CustomSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
//    private final JWTUtil jwtUtil;
    private final JWTFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf disable
        // stateless 상태로 관리하기 때문에 csrf는 끊다.
        http
                .csrf((auth) -> auth.disable());

        // Form 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());




        // JWTFilter 등록
//        http
//                .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);
        http
                .addFilterBefore(jwtFilter, OAuth2LoginAuthenticationFilter.class);




        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(

                                "/api/**"
//                                "/api/v1/auth/signin"
//                                , "/api/v1/auth/signup"
//                                , "/api/v1/auth/mail-send"
//                                , "/api/v1/auth/mail-check"
//                                , "/api/v1/auth/id-duplicate-check"
//                                , "/api/v1/category/search/{categorySeq}"
//                                , "/api/v1/category/big-categories"
//                                , "/api/v1/category/mid-by-big"
//                                , "/api/v1/category/small-by-mid"
//                                , "/api/v1/contents/{productseq}"
//                                , "/api/v1/events"
//                                , "/api/v1/optionstock/{productId}"
//                                , "/api/v1/products/search"
//                                , "/api/v1/products/filter"
//                                , "/api/v1/products/{id}"
//                                , "/api/v1/products/productsListCard/{id}"
//                                , "/api/v1/products/event/{id}"
//                                , "/api/v1/products/best"
//                                , "/api/v1/likes/check/product-seq/{productSeq}"
//                                , "/api/v1/reviews/{reviewSeq}"
//                                , "/api/v1/reviews/products/{productSeq}"
//                                , "/api/v1/purchase/non-member-check/{purchaseCode}"
//                                , "/api/v1/purchase/non-member-purchase"
//                                , "/api/v1/purchaseproduct/update/purchaseProductState/{purchaseProductSeq}"

                                , "/error"

                                , "/swagger-ui/**"
                                , "/swagger-resources/**"
                                , "/api-docs/**")
                        .permitAll()
                        .anyRequest().authenticated());

        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
