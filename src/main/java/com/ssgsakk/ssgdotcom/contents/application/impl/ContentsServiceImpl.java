package com.ssgsakk.ssgdotcom.contents.application.impl;

import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ProductContentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {
    ProductContentsRepository productContentsRepository;
    @Override
    @Transactional
    public List<ProductContents> productContentsList(Long ProductSeq){
        return productContentsRepository.findByProduct_ProductSeq(ProductSeq);
    }
}
