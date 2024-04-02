package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;

import java.util.List;

public interface CartService {
    List<CartDto> addCart(List<CartRequestVo> cartRequestVo);
    List<Cart> getCartItems();
}
