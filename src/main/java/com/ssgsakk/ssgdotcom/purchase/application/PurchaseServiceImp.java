package com.ssgsakk.ssgdotcom.purchase.application;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseAndPurchaseProductDto;
import com.ssgsakk.ssgdotcom.purchase.infrastructure.PurchaseRepository;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateMemberPurchaseRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

}