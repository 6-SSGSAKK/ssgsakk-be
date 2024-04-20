package com.ssgsakk.ssgdotcom.purchase.vo;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CreateNonMemberPurchaseRequestVo { //비회원 주문 VO (배송)

    private String purchaser; //주문자이름
    private String purchaserPhoneNum; //주문자 전화번호
    private String purchaseEmail; //주문자 이메일
    private String recipient; //수령자 이름
    private String recipientPhoneNum; //수령자 전화번호
    private String recipientEmail; //수령자 이메일
    private String purchaseZipcode; //최종배송지우편번호
    private String purchaseRoadAddress; //최종배송지 도로명주소
    private String purchaseJibunAddress; //최종배송지지번주소
    private String purchaseDetailAddress; //최종배송지상세주소
    private String deliverymessage; //배송메시지
    private Long shippingFee; //배송비
    // 여기까지가 PurchaseDTO로

    private List<PurchaseProductDto> purchaseProductDtoList; //주문상품리스트

    public static PurchaseDto voToPurchaseDto (CreateNonMemberPurchaseRequestVo createNonMemberPurchaseRequestVo) {
        return PurchaseDto.builder()
                .purchaser(createNonMemberPurchaseRequestVo.getPurchaser())
                .purchaserPhoneNum(createNonMemberPurchaseRequestVo.getPurchaserPhoneNum())
                .purchaseEmail(createNonMemberPurchaseRequestVo.getPurchaseEmail())
                .recipient(createNonMemberPurchaseRequestVo.getRecipient())
                .recipientPhoneNum(createNonMemberPurchaseRequestVo.getRecipientPhoneNum())
                .recipientEmail(createNonMemberPurchaseRequestVo.getRecipientEmail())
                .purchaseZipcode(createNonMemberPurchaseRequestVo.getPurchaseZipcode())
                .purchaseRoadAddress(createNonMemberPurchaseRequestVo.getPurchaseRoadAddress())
                .purchaseJibunAddress(createNonMemberPurchaseRequestVo.getPurchaseJibunAddress())
                .purchaseDetailAddress(createNonMemberPurchaseRequestVo.getPurchaseDetailAddress())
                .deliverymessage(createNonMemberPurchaseRequestVo.getDeliverymessage())
                .shippingFee(createNonMemberPurchaseRequestVo.getShippingFee())
                .build();
    } //Vo->PurchaseDto
    public static List<PurchaseProductDto> voToPurchaseProductDtoList(List<PurchaseProductDto> purchaseProductList) {
        List<PurchaseProductDto> purchaseProductDtoList = new ArrayList<>();
        for (PurchaseProductDto purchaseProduct : purchaseProductList) {
            PurchaseProductDto purchaseProductDto = PurchaseProductDto.builder()
                    .productSeq(purchaseProduct.getProductSeq())
                    .purchaseProductName(purchaseProduct.getPurchaseProductName())
                    .purchaseProductVendor(purchaseProduct.getPurchaseProductVendor())
                    .productOptionSeq(purchaseProduct.getProductOptionSeq())
                    .purchaseProductCount(purchaseProduct.getPurchaseProductCount())
                    .purchaseProductPrice(purchaseProduct.getPurchaseProductPrice())
                    .purchaseProductOptionName(purchaseProduct.getPurchaseProductOptionName())
                    .purchaseProductDiscountPrice(purchaseProduct.getPurchaseProductDiscountPrice())
                    .productThumbnail(purchaseProduct.getProductThumbnail())
                    .deliveryType(purchaseProduct.getDeliveryType())
                    .productState(purchaseProduct.getProductState())
                    .build();
            purchaseProductDtoList.add(purchaseProductDto);
        }
        return purchaseProductDtoList; //Vo->PurchaseProductDto
    }


}
