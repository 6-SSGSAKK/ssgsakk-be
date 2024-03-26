package com.ssgsakk.ssgdotcom.member.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@NoArgsConstructor
public class OAuth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long oauthSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(nullable = false, length = 20)
    private String oauthType;
    @Column(nullable = false, length = 255)
    private String oauthId;

    @Builder
    public OAuth(long oauthSeq, User user, String oauthType, String oauthId) {
        this.oauthSeq = oauthSeq;
        this.user = user;
        this.oauthType = oauthType;
        this.oauthId = oauthId;
    }
}
