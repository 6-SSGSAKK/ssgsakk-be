package com.ssgsakk.ssgdotcom.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestVo {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userMobileNum;
}
