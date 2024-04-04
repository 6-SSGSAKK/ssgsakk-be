package com.ssgsakk.ssgdotcom.purchase.presentation;
import com.ssgsakk.ssgdotcom.purchase.application.PurchaseService;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateMemberPurchaseRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;


    @PostMapping("/member-purchase") //CreateMemberPurchaseRequestVo -> PurchaseAndPUrchaseProductDto로 반환
    public void createMemberPurchase(@RequestBody CreateMemberPurchaseRequestVo createMemberPurchaseRequestVo) {


    }
}
