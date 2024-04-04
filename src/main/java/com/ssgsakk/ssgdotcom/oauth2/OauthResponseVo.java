package com.ssgsakk.ssgdotcom.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OauthResponseVo {
    private String token;
    private boolean state;       // true면 기존 고객, false면 신규 고객
    private String userName;
    private String userEmail;
    private String oAuthId;

    @Builder
    public OauthResponseVo(String token, boolean state, String userName, String userEmail, String oAuthId) {
        this.token = token;
        this.state = state;
        this.userName = userName;
        this.userEmail = userEmail;
        this.oAuthId = oAuthId;
    }
}