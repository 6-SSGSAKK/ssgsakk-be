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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String uuid;
}
