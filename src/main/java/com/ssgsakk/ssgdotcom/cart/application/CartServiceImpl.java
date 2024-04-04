package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import com.ssgsakk.ssgdotcom.option.infrastructure.OptionAndStockRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssgsakk.ssgdotcom.cart.dto.CartDto.EntityToDto;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final OptionAndStockRepository optionAndStockRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @Transactional
    @Override
    public String addCart(CartDto cartDto, String accessToken) {
        Cart existingCart = cartRepository.checkCart(cartDto.getOptionAndStockSeq(), checkUser(accessToken).getUserSeq())
                .orElse(null);

        if (existingCart != null) {
            int updatedQuantity = existingCart.getQuantity() + 1;
            updateQuantityInEntity(existingCart, updatedQuantity);
            return String.format("한번 더 담으셨네요!\n담긴 수량이 %d개가 되었습니다.", updatedQuantity);
        } else {
            cartRepository.save(convertToEntity(cartDto, accessToken));
            return("success");
        }
    }

    @Transactional
    @Override
    public CartDto getCart(Long cartSeq, String accessToken) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, accessToken);
        return EntityToDto(cart);
    }

    @Transactional
    @Override
    public void deleteCart(Long cartSeq, String accessToken) {
        checkUserCart(checkcart(cartSeq), accessToken);
        cartRepository.deleteById(cartSeq);
    }

    @Transactional
    @Override
    public void updateQuantity(Long cartSeq, Integer quantity, String accessToken) {
        Cart cart = checkcart(cartSeq) ;
        checkUserCart(cart, accessToken);
        updateQuantityInEntity(cart,quantity);
    }

    @Transactional
    @Override
    public void updateOption(Long cartSeq, Long optionAndStockSeq, String accessToken) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, accessToken);
        updateOptionInEntity(cart,optionAndStockSeq);
    }

    @Transactional
    @Override
    public Integer getCartCount(String accessToken) {
        return cartRepository.countByUser_UserSeq(checkUser(accessToken).getUserSeq());
    }

    @Transactional
    @Override
    public void updateCartPin(Long cartSeq, Short fixItem, String accessToken) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, accessToken);
        updateCartPinInEntity(cart,fixItem);
    }

    @Transactional
    @Override
    public void updateCheckbox(Long cartSeq, Short checkbox, String accessToken) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, accessToken);
        updateCartCheckInEntity(cart,checkbox);
    }

    @Transactional
    @Override
    public List<CartDto> getCartList(String accessToken) {
        return convertToDto(cartRepository.findByUser_UserSeq(checkUser(accessToken).getUserSeq()));
    }
    private List<CartDto> convertToDto(List<Cart> cartList) {
        return cartList.stream()
                .map(cart -> CartDto.builder()
                        .cartSeq(cart.getCartSeq())
                        .checkbox(cart.getCheckbox())
                        .fixItem(cart.getFixItem())
                        .optionAndStockSeq(cart.getOptionAndStock().getOptionAndStockSeq())
                        .productSeq(cart.getProduct().getProductSeq())
                        .quantity(cart.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private Cart checkcart(Long cartSeq){
        return cartRepository.findById(cartSeq)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }

    private User checkUser(String accessToken) {
        return memberRepository.findByUuid(jwtUtil.getUuid(accessToken))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    private void checkUserCart(Cart cart, String accessToken) {
        User user = cart.getUser();

        if (!user.getUuid().equals(jwtUtil.getUuid(accessToken))) {
            throw new IllegalArgumentException("User not authorized");
        }
    }

    private Cart convertToEntity(CartDto cartDto, String accessToken) {
        OptionAndStock optionAndStock = optionAndStockRepository.findById(cartDto.getOptionAndStockSeq())
                .orElseThrow(() -> new IllegalArgumentException("OptionAndStock not found"));
        Product product = productRepository.findById(cartDto.getProductSeq())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return Cart.builder()
                .user(checkUser(accessToken))
                .optionAndStock(optionAndStock)
                .product(product)
                .quantity(cartDto.getQuantity())
                .checkbox(cartDto.getCheckbox())
                .fixItem(cartDto.getFixItem())
                .build();
    }
    private void updateQuantityInEntity(Cart cart, Integer quantity) {
        Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(quantity)
                .checkbox(cart.getCheckbox())
                .fixItem(cart.getFixItem())
                .build();
    }

    private void updateOptionInEntity(Cart cart, Long optionAndStockSeq) {
        Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(optionAndStockRepository.findById(optionAndStockSeq)
                        .orElseThrow(() -> new IllegalArgumentException("OptionAndStock not found")))
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(cart.getCheckbox())
                .fixItem(cart.getFixItem())
                .build();
    }

    private void updateCartPinInEntity(Cart cart, Short fixItem) {
        Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(cart.getCheckbox())
                .fixItem(fixItem)
                .build();
    }

    private void updateCartCheckInEntity(Cart cart, Short checkbox) {
        Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(checkbox)
                .fixItem(cart.getFixItem())
                .build();
    }
}