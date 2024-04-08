package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInforResponseVo {
    private String userId;
    private String userName;
    private String userEmail;
    private String userMobileNum;

    @Builder
    public UserInforResponseVo(String userId, String userName, String userEmail, String userMobileNum) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobileNum = userMobileNum;
    }
}
