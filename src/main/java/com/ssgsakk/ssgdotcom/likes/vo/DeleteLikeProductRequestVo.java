package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class DeleteLikeProductRequestVo {
    private List<Long> likeProductSeqList;
    
    @Builder
    public DeleteLikeProductRequestVo(List<Long> likeProductSeqList) {
        this.likeProductSeqList = likeProductSeqList;
    }
}
