package com.ssgsakk.ssgdotcom.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MobileNumChangeDto {
    private String mobileNum;
    private String uuid;
    @Builder
    public MobileNumChangeDto(String mobileNum, String uuid) {
        this.mobileNum = mobileNum;
        this.uuid = uuid;
    }
}
