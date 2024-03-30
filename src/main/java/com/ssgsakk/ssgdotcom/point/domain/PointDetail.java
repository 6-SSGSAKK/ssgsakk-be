package com.ssgsakk.ssgdotcom.point.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class PointDetail extends BaseCreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointDetailSeq;

    @Column(nullable = false, length = 20, unique = true)
    private String uuid;

    @Column(nullable = false)
    private int pointChange;

    @Column(nullable = false)
    private int pointState;      // 0이면 적립, 1이면 사용

    @Column(nullable = false)
    private LocalDateTime pointExpired;

    @Builder
    public PointDetail(Long pointDetailSeq, String uuid, int pointChange, int pointState) {
        this.pointDetailSeq = pointDetailSeq;
        this.uuid = uuid;
        this.pointChange = pointChange;
        this.pointState = pointState;
        this.pointExpired = getCreatedDate().plusYears(1);      // 만료일 생성일 기준으로 자동 설정
    }

    @PrePersist
    public void setPointExpired() {
        // 현재 시간을 기준으로 1년 후의 시간을 계산하여 pointExpired 필드에 할당
        this.pointExpired = getCreatedDate().plusYears(1);
    }
}
