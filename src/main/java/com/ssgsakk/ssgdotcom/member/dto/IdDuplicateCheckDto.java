package com.ssgsakk.ssgdotcom.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdDuplicateCheckDto {
    private String inputId;

    @Builder
    public IdDuplicateCheckDto(String inputId) {
        this.inputId = inputId;
    }
}
