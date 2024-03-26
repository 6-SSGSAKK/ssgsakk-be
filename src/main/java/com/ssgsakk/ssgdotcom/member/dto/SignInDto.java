package com.ssgsakk.ssgdotcom.member.dto;

import lombok.*;

/**
 * 로그인에 필요한 데이터 명시
 *  userId, userPassword, userName, uuid
 *
 *  Dto는 서비스 로직에 따라서 수정이 가능해야 하므로 Getter, Setter 존재
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignInDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String uuid;
    private String token;

    @Builder
    public SignInDto(String userId, String userPassword, String userName, String uuid, String token) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.uuid = uuid;
        this.token = token;
    }
}
