package com.ssgsakk.ssgdotcom.point.application.impl;

import com.ssgsakk.ssgdotcom.point.application.PointService;
import com.ssgsakk.ssgdotcom.point.infrastructure.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

}
