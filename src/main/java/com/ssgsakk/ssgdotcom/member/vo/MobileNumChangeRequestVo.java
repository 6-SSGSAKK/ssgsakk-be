package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MobileNumChangeRequestVo {
    private String mobileNum;

    @Builder
    public MobileNumChangeRequestVo(String mobileNum) {
        this.mobileNum = mobileNum;
    }
}
