package com.ssgsakk.ssgdotcom.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseVo {
    private String userName;
    private String uuid;
    private String token;
}
