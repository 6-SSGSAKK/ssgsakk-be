package com.ssgsakk.ssgdotcom.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordChangeDto {
    private String password;
    private String uuid;

    @Builder
    public PasswordChangeDto(String password, String uuid) {

        this.password = password;
        this.uuid = uuid;
    }
}
