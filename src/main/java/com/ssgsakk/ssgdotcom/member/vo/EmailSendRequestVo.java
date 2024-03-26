package com.ssgsakk.ssgdotcom.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EmailSendRequestVo {
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String email;
}
