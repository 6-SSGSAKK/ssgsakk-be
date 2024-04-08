package com.ssgsakk.ssgdotcom.member.vo;

import lombok.*;

@Getter
@NoArgsConstructor
public class SignInRequestVo {
    private String userId;
    private String userPassword;

    @Builder
    public SignInRequestVo(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
