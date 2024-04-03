package com.ssgsakk.ssgdotcom.common.exception;

import lombok.Getter;
import org.hibernate.Internal;
import org.webjars.NotFoundException;

@Getter
public enum ErrorCode {

    // 400 번대 에러
    TEST_ERROR_CODE(400, "ERROR-Client-001", "잘못된 요청입니다."),

    // Token, Code
    TOKEN_EXPIRED(401, "ERROR-AUTH-001", "토큰이 만료되었습니다."),
    TOKEN_NOT_VALID(401, "ERROR-AUTH-002", "토큰이 유효하지 않습니다."),
    TOKEN_NULL(401 , "ERROR-AUTH-003", "토큰이 존재하지 않습니다."),
    JWT_CREATE_FAILED(401 , "ERROR-AUTH-004", "토큰 생성에 실패했습니다."),
    JWT_VALID_FAILED(401 , "ERROR-AUTH-005", "토큰 검증에 실패했습니다."),
    EXPIRED_AUTH_CODE(401 , "ERROR-AUTH-006", "인증번호가 만료되었거나 존재하지 않는 멤버입니다."),
    WRONG_AUTH_CODE(401 , "ERROR-AUTH-007", "인증번호가 일치하지 않습니다."),

    // Members
    DUPLICATE_EMAIL(409, "ERROR-AUTH-008",  "사용중인 이메일입니다."),
    DUPLICATED_MEMBERS(409, "ERROR-AUTH-009",  "이미 가입된 멤버입니다."),
    MASSAGE_SEND_FAILED(406, "ERROR-AUTH-010",  "인증번호 전송에 실패했습니다."),
    MASSAGE_VALID_FAILED(406, "ERROR-AUTH-011",  "인증번호가 일치하지 않습니다."),
    FAILED_TO_LOGIN(409, "ERROR-AUTH-012",  "아이디 또는 패스워드를 다시 확인하세요."),
    WITHDRAWAL_MEMBERS(409, "ERROR-AUTH-013",  "탈퇴한 회원입니다."),
    NO_EXIST_MEMBERS(404, "ERROR-AUTH-014",  "존재하지 않는 멤버 정보입니다."),
    MEMBERS_STATUS_IS_NOT_FOUND(404, "ERROR-AUTH-015",  "존재하지 않는 멤버 상태입니다."),
    PASSWORD_SAME_FAILED(409, "ERROR-AUTH-016",  "현재 사용중인 비밀번호 입니다."),
    PASSWORD_CONTAIN_NUM_FAILED(406, "ERROR-AUTH-017",  "휴대폰 번호를 포함한 비밀번호 입니다."),
    PASSWORD_CONTAIN_EMAIL_FAILED(406, "ERROR-AUTH-018",  "이메일이 포함된 비밀번호 입니다."),
    DUPLICATE_ID(406, "ERROR-AUTH-019",  "이미 가입된 ID 입니다."),
    DUPLICATE_MOBILE_NUM(406, "ERROR-AUTH-020",  "이미 존재하는 전화번호 입니다."),


    //Category
    CANNOT_FOUND_CATEGORY(404,"ERROR-CATEGORY-001","카테고리를 찾을수없습니다."),

    //Purchase
    CANNOT_FOUND_PURCHASE(404,"ERRROE-PURCHASE-001","주문을 찾을수 없습니다."),

    // 500 번대 에러
    INTERNAL_SERVER_ERROR(500, "ERROR-SERVER-001", "서버에서 요청을 처리하지 못했습니다."),
    REDIS_SERVER_ERROR(500, "ERROR-SERVER-002", "레디스 서버에서 요청을 처리하지 못했습니다.");






    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }


}