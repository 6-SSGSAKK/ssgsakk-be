package com.ssgsakk.ssgdotcom.point.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsePointDto {
    private int pointChange;
    private String uuid;
    @Builder
    public UsePointDto(int pointChange, String uuid) {
        this.pointChange = pointChange;
        this.uuid = uuid;
    }
}
