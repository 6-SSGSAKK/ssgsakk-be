package com.ssgsakk.ssgdotcom.purchase.application;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import com.ssgsakk.ssgdotcom.purchase.dto.MemberPurchaseSeqListDto;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseCodeDto;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchase.infrastructure.PurchaseRepository;
import com.ssgsakk.ssgdotcom.purchase.infrastructure.PurchaseRepositoryImp;
import com.ssgsakk.ssgdotcom.purchaseproduct.application.PurchaseProductService;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductService purchaseProductService;
    private final PurchaseRepositoryImp purchaseRepositoryImp;

    public String createdMemberPurchaseCode(){  //주문번호 생성 메소드  회원 주문번호 메소드

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String currentDate = dateFormat.format(new Date()); //현재 날짜 시간.
        String makedpurchaseCode = currentDate + "-U-"; //날짜 + U


        StringBuilder randomCodeBuilder = new StringBuilder();
        Random random = new Random();

        for(int i=0; i<6; i++){  //0~9까지 난수생성
            randomCodeBuilder.append(random.nextInt(10));
        }

        makedpurchaseCode += randomCodeBuilder.toString();
        return makedpurchaseCode; //purchseCode 생성
    }

    public String createNonMemberPurchaseCode(){

       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
       String currentDate = dateFormat.format(new Date()); //현재 날짜 시간.
       String makepurchaseCode = currentDate + "-N-"; //비회원은 날짜 + N으로 시작

       Random random = new Random();

       StringBuilder randomCodeBuilder = new StringBuilder();

       for (int i = 0; i < 6; i++) { //0~9까지 난수생성
           randomCodeBuilder.append(random.nextInt(10));
       }

        makepurchaseCode += randomCodeBuilder.toString();
       return makepurchaseCode;

    }


    @Override
    @Transactional
    public PurchaseCodeDto createMemberPurchase(PurchaseDto purchaseDto, List<PurchaseProductDto> purchaseProductDto) {
        // 주문정보 생성
        //PurchaseProductService에 있는 메소드를 사용하기 위해 필요한 List<PurchaseProductDto> 도 받아옴
        Purchase purchase = Purchase.builder()
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
                        .build(); //purchaseCode는 return해야하니깐 purchseCodeDTO에 담음.

        return purchaseCodeDto;


    }

    @Override
    @Transactional
    public PurchaseCodeDto createNonMemberPurchase(PurchaseDto purchaseDto, List<PurchaseProductDto> purchaseProductDto) {
        //비회원 주문저장 메소드
        Purchase purchase = Purchase.builder()
                .purchaser(purchaseDto.getPurchaser())
                .purchaseCode(createNonMemberPurchaseCode())
                .purchaseuuid(null)
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
                .build(); //위와동일

        return purchaseCodeDto;
    }



    //checkMemeberPurchase 에서 리턴으로 받은 튜플 리스트를 DTO로 전달
    private MemberPurchaseSeqListDto mapTupleToDto(com.querydsl.core.Tuple tuple){
        Long purchaseSeq = tuple.get(QPurchase.purchase.purchaseSeq);
        String uuid = tuple.get(QPurchase.purchase.purchaseuuid);
        return new MemberPurchaseSeqListDto(purchaseSeq, uuid);
    }

    @Transactional
    @Override
    public List<MemberPurchaseSeqListDto> checkMemberPurchase(String uuid) { //회원별 purchaseSeq 를 조회하는 메소드

        List<com.querydsl.core.Tuple> tuples = purchaseRepositoryImp.checkMemberPurchase(uuid);

        //튜플에서 꺼내온 값들로 이루어진 DTO를 다시 List 형태로 변환해서 리턴함
        List<MemberPurchaseSeqListDto> memberPurchaseSeqListDto = tuples.stream()
                .map(this::mapTupleToDto)
                .collect(Collectors.toList());

        return memberPurchaseSeqListDto;

    }


}