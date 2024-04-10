package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class DeleteLikeCategoryRequestVo {
    private List<Long> likeCategorySeqList;

    @Builder
    public DeleteLikeCategoryRequestVo(List<Long> likeCategorySeqList) {
        this.likeCategorySeqList = likeCategorySeqList;
    }
}
