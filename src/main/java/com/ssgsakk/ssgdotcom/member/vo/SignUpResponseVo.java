package com.ssgsakk.ssgdotcom.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponseVo {
    private String userName;
    private String uuid;

    @Builder
    public SignUpResponseVo(String userName, String uuid) {
        this.userName = userName;
        this.uuid = uuid;
    }
}
