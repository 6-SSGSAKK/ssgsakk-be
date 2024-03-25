package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendResponseVo {
    private String email;
    private String authNum;

    @Builder
    public EmailSendResponseVo(String email, String authNum) {
        this.email = email;
        this.authNum = authNum;
    }
}
