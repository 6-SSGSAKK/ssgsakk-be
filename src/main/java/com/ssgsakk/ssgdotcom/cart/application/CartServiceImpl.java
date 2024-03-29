package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Override
    @Transactional
    public List<CartDto> addCart(List<CartRequestVo> cartRequestVo) {

        return null;
    }

    @Override
    public List<Cart> getCartItems() {
        return cartRepository.findAll();
    }
}