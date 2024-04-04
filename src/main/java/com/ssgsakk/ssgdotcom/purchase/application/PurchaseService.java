package com.ssgsakk.ssgdotcom.purchase.application;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseAndPurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateMemberPurchaseRequestVo;


public interface PurchaseService {

    void createPurchaseAndPurchaseProductDto(CreateMemberPurchaseRequestVo createMemberPurchaseRequestVo);
}