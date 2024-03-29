package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class passwordChangeRequestVo {
    private String password;

    @Builder
    public passwordChangeRequestVo(String password) {
        this.password = password;
    }
}
