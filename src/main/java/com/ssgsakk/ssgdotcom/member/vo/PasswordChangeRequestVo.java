package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordChangeRequestVo {
    private String password;

    @Builder
    public PasswordChangeRequestVo(String password) {
        this.password = password;
    }
}
