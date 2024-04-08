package com.ssgsakk.ssgdotcom.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdDuplicateCheckRequestVo {
    private String inputId;

    @Builder
    public IdDuplicateCheckRequestVo(String inputId) {
        this.inputId = inputId;
    }
}
