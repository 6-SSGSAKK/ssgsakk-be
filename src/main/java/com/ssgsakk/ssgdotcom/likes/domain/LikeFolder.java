package com.ssgsakk.ssgdotcom.likes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class LikeFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeFolderSeq;

    private String likeFolderName;

    @Builder
    public LikeFolder(Long likeFolderSeq, String likeFolderName) {
        this.likeFolderName = likeFolderName;
        this.likeFolderName = likeFolderName;
    }
}
