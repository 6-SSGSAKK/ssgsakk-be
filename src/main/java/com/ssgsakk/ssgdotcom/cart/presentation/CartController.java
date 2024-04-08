package com.ssgsakk.ssgdotcom.cart.presentation;

import com.ssgsakk.ssgdotcom.cart.application.CartService;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import com.ssgsakk.ssgdotcom.cart.vo.CartResponseVo;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;

import static com.ssgsakk.ssgdotcom.cart.dto.CartDto.VoToDto;
import static com.ssgsakk.ssgdotcom.cart.vo.CartResponseVo.DtoToVo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public BaseResponse<?> addCart(@RequestBody CartRequestVo cartRequestVo,
                                   @RequestHeader("Authorization") String accessToken) {
        // 생성된 CartDto 리스트를 서비스로 전달하여 카트에 추가
        String response = cartService.addCart(VoToDto(cartRequestVo), accessToken);
        return new BaseResponse<>("add cart success", response);
    }

    @GetMapping("/list")
    public BaseResponse<List<CartResponseVo>> getCartItems(@RequestHeader("Authorization") String accessToken) {
        List<CartDto> cartItemList = cartService.getCartList(accessToken);
        List<CartResponseVo> cartResponseVoList = CartResponseVo.DtoListToVoList(cartItemList);
        return new BaseResponse<>("get cart items success", cartResponseVoList);
    }

    @GetMapping("/{cartId}")
    public BaseResponse<CartResponseVo> getCart(@RequestHeader("Authorization") String accessToken,
                                                @PathVariable("cartId") Long cartId) {
        return new BaseResponse<>("get cart success", DtoToVo(cartService.getCart(cartId, accessToken)));
    }

    @PutMapping("/{cartId}/quantity")
    public BaseResponse<?> updateQuantity(@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("cartId") Long cartId,
                                          @RequestParam Integer quantity) {
        cartService.updateQuantity(cartId, quantity, accessToken);
        return new BaseResponse<>("update quantity success","");
    }

    @PutMapping("/{cartId}/option")
    public BaseResponse<?> updateOption(@RequestHeader("Authorization") String accessToken,
                                        @PathVariable("cartId") Long cartId,
                                        @RequestParam Long optionAndStockSeq) {
        cartService.updateOption(cartId, optionAndStockSeq, accessToken);
        return new BaseResponse<>("update option success","");
    }

    @PutMapping("/{cartId}/pin")
    public BaseResponse<?> updateCartPin(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("cartId") Long cartId,
                                         @RequestParam Short fixItem) {
        cartService.updateCartPin(cartId, fixItem, accessToken);
        return new BaseResponse<>("update pin success","");
    }

    @PutMapping("/{cartId}/checkbox")
    public BaseResponse<?> updateCheckbox(@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("cartId") Long cartId,
                                          @RequestParam Short checkbox) {
        cartService.updateCheckbox(cartId, checkbox, accessToken);
        return new BaseResponse<>("update checkbox success","");
    }
    @GetMapping("count")
    public BaseResponse<?> getCartCount(@RequestHeader("Authorization") String accessToken) {
        return new BaseResponse<>("get count success", cartService.getCartCount(accessToken));
    }

    @DeleteMapping("/{cartId}")
    public BaseResponse<?> deleteCart(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId, accessToken);
        return new BaseResponse<>("delete success","");
    }
}