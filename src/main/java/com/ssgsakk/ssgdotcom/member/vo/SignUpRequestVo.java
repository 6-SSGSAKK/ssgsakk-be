package com.ssgsakk.ssgdotcom.member.vo;

import jakarta.persistence.Column;
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

    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;

    private String oauthId;
}
