package com.ssgsakk.ssgdotcom.member.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestVo {
    private String userId;
    private String userPassword;
}
