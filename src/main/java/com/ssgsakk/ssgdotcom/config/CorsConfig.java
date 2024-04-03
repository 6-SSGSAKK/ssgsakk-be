package com.ssgsakk.ssgdotcom.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("https://ssgsakk-fe.vercel.app/", "http://localhost:3000", "https://www.ssgssak.shop/")
                .allowedMethods(CorsConfiguration.ALL)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .exposedHeaders(CorsConfiguration.ALL)
                .allowCredentials(false);
    }
}
