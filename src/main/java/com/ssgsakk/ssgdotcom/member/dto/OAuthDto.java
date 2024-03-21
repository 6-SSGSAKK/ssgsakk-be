package com.ssgsakk.ssgdotcom.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthDto {
    // role은 SSGUSER, OAUTHUSER, ALLUSER로 나뉨
    // JWT에 사용은 안 함. 그래서 필요 없을 듯 하다.
    private String role;
    private String name;
    private String email;
}