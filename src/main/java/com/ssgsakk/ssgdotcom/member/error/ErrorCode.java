package com.ssgsakk.ssgdotcom.member.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND_WITH_USER_ID(HttpStatus.NOT_FOUND, "MEMBER-001", "ID를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "MEMBER-002", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;    // HttpStatus
    private final String code;              // MEMBER-001, MEMBER-002, MEMBER-003,,,,
    private final String message;           // 에러 코드 설명

}
