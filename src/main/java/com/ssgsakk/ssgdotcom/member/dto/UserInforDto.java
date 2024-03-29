package com.ssgsakk.ssgdotcom.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserInforDto {
    private String userId;
    private String userName;
    private String userEmail;
    private String userMobileNum;

    @Builder
    public UserInforDto(String userId, String userName, String userEmail, String userMobileNum) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobileNum = userMobileNum;
    }
}
