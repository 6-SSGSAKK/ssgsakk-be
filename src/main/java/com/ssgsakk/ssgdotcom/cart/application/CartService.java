package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.dto.CartDto;


import java.util.List;

public interface CartService {
    String addCart(CartDto cartDto, String accessToken);

    List<CartDto> getCartList(String accessToken);

    CartDto getCart(Long cartSeq, String accessToken);

    void deleteCart(Long cartSeq, String accessToken);

    void updateQuantity(Long cartSeq, Integer quantity, String accessToken);

    void updateOption(Long cartSeq, Long optionAndStockSeq, String accessToken);

    Integer getCartCount(String accessToken);

    void updateCartPin(Long cartSeq, Short fixItem, String accessToken);

    void updateCheckbox(Long cartSeq, Short checkbox, String accessToken);
}
