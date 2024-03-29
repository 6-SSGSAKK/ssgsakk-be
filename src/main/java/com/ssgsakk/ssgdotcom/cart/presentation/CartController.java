package com.ssgsakk.ssgdotcom.cart.presentation;

import com.ssgsakk.ssgdotcom.cart.application.CartService;
import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;
    private CartRepository cartRepository;
    @PostMapping("/add")
    public BaseResponse<?> addToCart(@RequestBody List<CartRequestVo> cartRequestVo) {
        cartService.addCart(cartRequestVo);
        return new BaseResponse<>("","");
    }

    @GetMapping("/items")
    public ResponseEntity<List<Cart>> getCartItems() {
        List<Cart> cartItems = cartService.getCartItems();
        return ResponseEntity.ok(cartItems);
    }
}