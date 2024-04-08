package com.ssgsakk.ssgdotcom.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OauthResponseVo {
    private String token;
    private int state;       // 0 : 기존회원, 1 : oauth테이블에는 없지만 user 테이블에는 있는 회원, 2 : 신규 회원
    private String userName;
    private String userEmail;
    private String oAuthId;

    @Builder
    public OauthResponseVo(String token, int state, String userName, String userEmail, String oAuthId) {
        this.token = token;
        this.state = state;
        this.userName = userName;
        this.userEmail = userEmail;
        this.oAuthId = oAuthId;
    }
}