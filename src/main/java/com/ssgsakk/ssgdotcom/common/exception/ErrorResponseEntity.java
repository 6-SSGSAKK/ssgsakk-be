package com.ssgsakk.ssgdotcom.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseEntity {
    private int status;
    private String code;
    private String message;

    public static ErrorResponseEntity of(int status, String code, String description){
        return ErrorResponseEntity.builder()
                .status(status)
                .code(code)
                .message(description)
                .build();
    }
}