package com.ssgsakk.ssgdotcom.member.dto;

import lombok.*;

/**
 * 로그인에 필요한 출력 데이터 명시
 *  userName, uuid
 *
 *  Dto는 서비스 로직에 따라서 수정이 가능해야 하므로 Getter, Setter 존재
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private String userName;
    private String uuid;
}
