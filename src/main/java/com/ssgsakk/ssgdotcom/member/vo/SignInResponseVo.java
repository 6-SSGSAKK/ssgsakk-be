package com.ssgsakk.ssgdotcom.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseVo {
    private String userName;
    private String token;

    @Builder
    public SignInResponseVo(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
}
