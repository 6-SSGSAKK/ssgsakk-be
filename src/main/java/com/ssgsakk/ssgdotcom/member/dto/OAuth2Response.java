package com.ssgsakk.ssgdotcom.member.dto;

public interface OAuth2Response {
    // 제공자 (naver, google ,,,,)
    String getProvider();

    // 제공자에서 발급해주는 아이디, 이메일, 이름
    String getProviderId();
    String getEmail();
    String getName();
}
