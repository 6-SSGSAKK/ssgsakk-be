//package com.ssgsakk.ssgdotcom.cart.presentation;
//
//import com.ssgsakk.ssgdotcom.cart.application.CartService;
//import com.ssgsakk.ssgdotcom.cart.domain.Cart;
//import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
//import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
//import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
//import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1/cart")
//public class CartController {
//    private CartService cartService;
//    private CartRepository cartRepository;
//    @PostMapping("/add")
//    public BaseResponse<?> addCart(@RequestBody List<CartRequestVo> cartRequestVoList) {
//        List<CartDto> cartDtoList = cartRequestVoList.stream()
//                .map(cartRequestVo -> CartDto.builder()
//                        .productSeq(cartRequestVo.getProductSeq())
//                        .quantity(cartRequestVo.getQuantity())
//                        .checkbox(cartRequestVo.getCheckbox())
//                        .fixItem(cartRequestVo.getFixItem())
//                        .optionAndStockId(cartRequestVo.getOptionAndStockId())
//                        .build())
//                .collect(Collectors.toList());
//        // 생성된 CartDto 리스트를 서비스로 전달하여 카트에 추가
//        cartService.addCart(cartDtoList);
//        return new BaseResponse<>("","");
//    }
//
//    @GetMapping("/items")
//    public ResponseEntity<List<Cart>> getCartItems() {
//        List<Cart> cartItems = cartService.getCartItems();
//        return ResponseEntity.ok(cartItems);
//    }
//}