package com.ssgsakk.ssgdotcom.point.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.point.application.PointDetailService;
import com.ssgsakk.ssgdotcom.point.application.PointService;
import com.ssgsakk.ssgdotcom.point.dto.UsePointDto;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/point")
@RequiredArgsConstructor
public class PointController {
    private final JWTUtil jwtUtil;
    private final PointService pointService;
    private final PointDetailService pointDetailService;

    @Operation(summary = "포인트 사용", description = "포인트 사용", tags = {"Use Point"})
    @GetMapping("/Use-Point")
    public BaseResponse<Object> usePoint(
            @RequestParam(name = "pointChange") int pointChange,
            @RequestHeader("Authorization") String accessToken
    ) {
        String uuid = getUuid(accessToken);

        // dto 변환
        UsePointDto usePointDto = UsePointDto.builder()
                .pointChange(pointChange)
                .uuid(uuid)
                .build();

        // 서비스 레이어로 전달
        pointDetailService.usePoint(usePointDto);

        return new BaseResponse<>("포인트 이력 데이터 추가", null);
    }

    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
