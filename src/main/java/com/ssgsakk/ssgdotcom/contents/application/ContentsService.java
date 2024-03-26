package com.ssgsakk.ssgdotcom.contents.application;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;

import java.util.List;

public interface ContentsService {
    List<ProductContents> productContentsList(Long ProductSeq);
}
