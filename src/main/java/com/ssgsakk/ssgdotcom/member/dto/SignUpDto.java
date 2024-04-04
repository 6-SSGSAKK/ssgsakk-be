package com.ssgsakk.ssgdotcom.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userMobileNum;
    private String uuid;

    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;

    private String oauthId;

    @Builder

    public SignUpDto(String userId, String userPassword, String userName, String userEmail, String userMobileNum, String uuid, String zipCode, String roadAddress, String jibunAddress, String detailAddress, String oauthId) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobileNum = userMobileNum;
        this.uuid = uuid;
        this.zipCode = zipCode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.oauthId = oauthId;
    }
}
