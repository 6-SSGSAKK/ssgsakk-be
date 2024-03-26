package com.ssgsakk.ssgdotcom.oauth2;

import lombok.Getter;

@Getter
public enum ProviderName {
    NAVER("naver"),
    GOOGLE("google");

    private final String providerName;

    ProviderName(String providerName) {
        this.providerName = providerName;
    }
}
