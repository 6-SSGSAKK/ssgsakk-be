package com.ssgsakk.ssgdotcom.purchase.application;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseCodeDto;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.infrastructure.PurchaseRepository;
import com.ssgsakk.ssgdotcom.purchaseproduct.application.PurchaseProductService;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImp implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductService purchaseProductService;

    public String createdMemberPurchaseCode(){  //주문번호 생성 메소드  회원 주문번호 메소드

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String currentDate = dateFormat.format(new Date()); //현재 날짜 시간.
        String makedpurchaseCode = currentDate + "-U-"; //날짜 + U


        StringBuilder randomCodeBuilder = new StringBuilder();
        Random random = new Random();

        for(int i=0; i<6; i++){
            randomCodeBuilder.append(random.nextInt(10));
        }

        makedpurchaseCode += randomCodeBuilder.toString();
        return makedpurchaseCode;
    }


    @Override
    @Transactional
    public PurchaseCodeDto createMemberPurchase(PurchaseDto purchaseDto, List<PurchaseProductDto> purchaseProductDto) {
        // 주문정보 생성
        Purchase purchase = Purchase.builder()
                .purchaser(purchaseDto.getPurchaser())
                .purchaser(purchaseDto.getPurchaser())
                .purchaseCode(createdMemberPurchaseCode())
                .purchaseuuid(purchaseDto.getPurchaseuuid())
                .purchaserPhoneNum(purchaseDto.getPurchaserPhoneNum())
                .purchaseEmail(purchaseDto.getPurchaseEmail())
                .recipient(purchaseDto.getRecipient())
                .recipientPhoneNum(purchaseDto.getRecipientPhoneNum())
                .recipientEmail(purchaseDto.getRecipientEmail())
                .purchaseZipcode(purchaseDto.getPurchaseZipcode())
                .purchaseRoadAddress(purchaseDto.getPurchaseRoadAddress())
                .purchaseJibunAddress(purchaseDto.getPurchaseJibunAddress())
                .purchaseDetailAddress(purchaseDto.getPurchaseDetailAddress())
                .deliverymessage(purchaseDto.getDeliverymessage())
                .shippingFee(purchaseDto.getShippingFee())
                .build();

        purchaseRepository.save(purchase);

        purchaseProductService.savePurchaseProductList(purchaseProductDto, purchase);

        PurchaseCodeDto purchaseCodeDto = PurchaseCodeDto.builder()
                        .purchaseCode(purchase.getPurchaseCode())
                        .build();

        return purchaseCodeDto;


    }



}