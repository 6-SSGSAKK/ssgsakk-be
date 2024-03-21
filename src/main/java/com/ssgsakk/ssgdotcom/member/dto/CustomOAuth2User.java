package com.ssgsakk.ssgdotcom.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuthDto oAuthDto;
    public CustomOAuth2User(OAuthDto oAuthDto) {
        this.oAuthDto = oAuthDto;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return oAuthDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuthDto.getName();
    }

    // getAttributes는 제공 회사마다 상이해서 안 쓰고 새로 만든 getUsername 메서드를 통해서 진행
    public String getEmail() {
        return oAuthDto.getEmail();
    }

    // JWT에 사용할 uuid가 필요하다.
    public String getUuid() {
        return oAuthDto.getUuid();
    }
}
