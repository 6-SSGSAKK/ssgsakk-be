package com.ssgsakk.ssgdotcom.common.util;

import lombok.Getter;

@Getter
public enum DeliveryType{
            Normal("일반 배송"),
            SSG("쓱배송"),
            EARLY("새벽배송"),
            TRADERS("트레이더스쓱배송"),
            DEPARTMENT("백화점");

    DeliveryType(String s) {
    }
}