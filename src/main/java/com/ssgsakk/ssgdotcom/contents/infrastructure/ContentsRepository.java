package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
}
