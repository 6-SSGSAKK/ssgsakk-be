package com.ssgsakk.ssgdotcom.point.application.impl;

import com.ssgsakk.ssgdotcom.point.application.PointDetailService;
import com.ssgsakk.ssgdotcom.point.domain.PointDetail;
import com.ssgsakk.ssgdotcom.point.dto.UsePointDto;
import com.ssgsakk.ssgdotcom.point.infrastructure.PointDetailRepository;
import com.ssgsakk.ssgdotcom.point.infrastructure.PointRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointDetailServiceImpl implements PointDetailService {
    private final PointDetailRepository pointDetailRepository;
    private final PointRepository pointRepository;

    @Override
    @Transactional
    public void usePoint(UsePointDto usePointDto) {
        // 전달받은 DTO를 가지고 엔티티를 저장
        pointDetailRepository.save(PointDetail.builder()
                .uuid(usePointDto.getUuid())
                .pointChange(usePointDto.getPointChange())
                .pointState(-1)
                .build());

        // point 테이블에 반영
        PointTableSave(usePointDto.getUuid(), usePointDto.getPointChange(), 0);
    }

    // point 사용 후 point 테이블 갱신하는 메서드
    public void PointTableSave(String uuid, int pointChange, int state) {
        pointRepository.pointUpdate(uuid, pointChange, state);
    }
}
