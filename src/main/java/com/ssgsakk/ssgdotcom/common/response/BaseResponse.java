package com.ssgsakk.ssgdotcom.common.response;

public record BaseResponse<T>(T ResponseVo) {

    // 요청에 성공한 경우 -> return 객체가 필요한 경우
    public BaseResponse(T ResponseVo) {
        this.ResponseVo = ResponseVo;
    }

    // 요청에 성공한 경우 -> return 객체가 필요 없는 경우
//    public BaseResponse() {
//        this(null);
//    }
}