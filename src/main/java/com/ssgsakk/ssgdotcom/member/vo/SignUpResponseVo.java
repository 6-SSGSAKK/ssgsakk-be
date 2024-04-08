package com.ssgsakk.ssgdotcom.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponseVo {
    private String userName;
    @Builder
    public SignUpResponseVo(String userName) {
        this.userName = userName;
    }
}
