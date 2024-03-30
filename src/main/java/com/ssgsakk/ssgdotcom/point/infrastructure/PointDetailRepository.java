package com.ssgsakk.ssgdotcom.point.infrastructure;

import com.ssgsakk.ssgdotcom.point.domain.PointDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointDetailRepository extends JpaRepository<PointDetail, Long> {
}
