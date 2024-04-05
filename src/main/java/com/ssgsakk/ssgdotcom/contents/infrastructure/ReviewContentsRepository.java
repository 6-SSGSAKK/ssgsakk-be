package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewContentsRepository extends JpaRepository<ReviewContents, Long> {

    List<ReviewContents> findByReview_ReviewSeq(Long reviewSeq);
}
