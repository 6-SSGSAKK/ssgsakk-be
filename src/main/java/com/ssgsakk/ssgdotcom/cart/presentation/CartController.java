package com.ssgsakk.ssgdotcom.cart.presentation;

import com.ssgsakk.ssgdotcom.cart.application.CartService;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.vo.CartInfoResponseVo;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import com.ssgsakk.ssgdotcom.cart.vo.CartResponseVo;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;

import static com.ssgsakk.ssgdotcom.cart.dto.CartDto.VoToDto;

import com.ssgsakk.ssgdotcom.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;
    private final JWTUtil jwtUtil;

    //장바구니 추가
    @PostMapping("/add")
    public BaseResponse<?> addCart(@RequestBody CartRequestVo cartRequestVo,
                                   @RequestHeader("Authorization") String accessToken) {
        // 생성된 CartDto 리스트를 서비스로 전달하여 카트에 추가
        String response = cartService.addCart(VoToDto(cartRequestVo), getUuid(accessToken));
        return new BaseResponse<>("add cart success", response);
    }

    // 장바구니 정보
    @GetMapping("/list")
    public BaseResponse<List<CartResponseVo>> getCartItems(@RequestHeader("Authorization") String accessToken) {
        List<CartDto> cartItemList = cartService.getCartList(getUuid(accessToken));
        List<CartResponseVo> cartResponseVoList = CartResponseVo.DtoListToVoList(cartItemList);
        return new BaseResponse<>("get cart items success", cartResponseVoList);
    }

    // 장바구니 품목 정보
    @GetMapping("/{cartId}")
    public BaseResponse<CartInfoResponseVo> getCart(@RequestHeader("Authorization") String accessToken,
                                                    @PathVariable("cartId") Long cartId) {

        return new BaseResponse<>("get cart success",
                CartInfoResponseVo.InfoDtoToVo(cartService.getCart(cartId, getUuid(accessToken))));
    }
    // 장바구니 품목 수량 변경

    @PutMapping("/{cartId}/quantity")
    public BaseResponse<?> updateQuantity(@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("cartId") Long cartId,
                                          @RequestParam Integer quantity) {
        cartService.updateQuantity(cartId, quantity, getUuid(accessToken));
        return new BaseResponse<>("update quantity success","");
    }

    // 장바구니 옵션 수정
    @PutMapping("/{cartId}/option")
    public BaseResponse<?> updateOption(@RequestHeader("Authorization") String accessToken,
                                        @PathVariable("cartId") Long cartId,
                                        @RequestParam Long option) {
        cartService.updateOption(cartId, option, getUuid(accessToken));
        return new BaseResponse<>("update option success","");
    }

    // 장바구니 고정
    @PutMapping("/{cartId}/pin")
    public BaseResponse<?> updateCartPin(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("cartId") Long cartId,
                                         @RequestParam Integer fix) {
        cartService.updateCartPin(cartId, fix, getUuid(accessToken));
        return new BaseResponse<>("update pin success","");
    }

    // 장바구니 체크박스 선택
    @PutMapping("/{cartId}/checkbox")
    public BaseResponse<?> updateCheckbox(@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("cartId") Long cartId,
                                          @RequestParam Integer checkbox) {
        cartService.updateCheckbox(cartId, checkbox, getUuid(accessToken));
        return new BaseResponse<>("update checkbox success","");
    }

    // 장바구니 모든 체크박스 선택
    @PutMapping("/allcheck")
    public BaseResponse<?> updateAllCheck(@RequestHeader("Authorization") String accessToken,
                                          @RequestParam Integer checkbox) {
        cartService.updateAllCheck(checkbox, getUuid(accessToken));
        return new BaseResponse<>("update all check success","");
    }

    // 장바구니 담긴 품목 개수
    @GetMapping("count")
    public BaseResponse<?> getCartCount(@RequestHeader("Authorization") String accessToken) {
        return new BaseResponse<>("get count success", cartService.getCartCount(getUuid(accessToken)));
    }

    // 장바구니 삭제
    @DeleteMapping("/{cartId}")
    public BaseResponse<?> deleteCart(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("cartId") Long cartId) {

        cartService.deleteCart(cartId, getUuid(accessToken));
        return new BaseResponse<>("delete success","");
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