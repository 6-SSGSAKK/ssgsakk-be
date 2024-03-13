package com.ssgsakk.ssgdotcom.member.dto;

import lombok.*;

/**
 * 회원가입에 필요한 출력 데이터 명시
 *  userName, uuid
 *
 *  Dto는 서비스 로직에 따라서 수정이 가능해야 하므로 Getter, Setter 존재
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private String userName;
    private String uuid;
}
