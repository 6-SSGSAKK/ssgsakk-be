package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;


import java.util.List;

public interface CartService {
    String addCart(CartDto cartDto, String uuid);

    List<CartDto> getCartList(String uuid);

    CartDto getCart(Long cartSeq, String uuid);

    void deleteCart(Long cartSeq, String uuid);

    void updateQuantity(Long cartSeq, Integer quantity, String uuid);

    void updateOption(Long cartSeq, Long optionAndStockSeq, String uuid);

    Integer getCartCount(String uuid);

    void updateCartPin(Long cartSeq, Integer fixItem, String uuid);

    void updateCheckbox(Long cartSeq, Integer checkbox, String uuid);
}
