package com.ssgsakk.ssgdotcom.common.response;

public record BaseResponse<T>(String msg, T result) {

    // 요청에 성공한 경우 -> result 반환하는 경우
    public BaseResponse(String msg, T result) {
        this.msg = msg;
        this.result = result;
    }

    // result를 반환하지 않는 경우를 처리하기 위한 정적 팩토리 메소드
    public static <T> BaseResponse<T> ofMessageOnly(String msg) {
        return new BaseResponse<>(msg, null);
    }
}