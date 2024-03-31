package com.ssgsakk.ssgdotcom.point.infrastructure;

import com.ssgsakk.ssgdotcom.point.domain.Point;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Point p SET p.point = p.point + (:state * :pointChange) WHERE p.uuid = :uuid")
    void pointUpdate(@Param("uuid") String uuid, @Param("pointChange") int pointChange, @Param("state") int state);
}
