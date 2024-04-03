//package com.ssgsakk.ssgdotcom.cart.application;
//
//import com.ssgsakk.ssgdotcom.cart.domain.Cart;
//import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
//import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
//import com.ssgsakk.ssgdotcom.cart.vo.CartRequestVo;
//import com.ssgsakk.ssgdotcom.product.application.ProductService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.ssgsakk.ssgdotcom.member.domain.QUser.user;
//
//@Service
//public class CartServiceImpl implements CartService {
//
//    private final CartRepository cartRepository;
//    private final ProductService productService;
//
//
//
//    @Transactional
//    @Override
//    public List<CartDto> addCart(List<CartRequestVo> cartDtos) {
//        return cartDtos.stream()
//                .map(cartDto -> {
//                    Cart cart = Cart.builder()
//                            .user(user) // 예를 들어 현재 로그인된 사용자 정보를 가져와야 합니다.
//                            .optionAndStock(cartDto.getOptionAndStockId()) // optionAndStock 정보를 어떻게 가져올지에 대한 로직이 필요합니다.
//                            .product(productService.getProductById(cartDto.getProductId()))
//                            .quantity(cartDto.getQuantity())
//                            .checkbox(cartDto.getCheckbox())
//                            .fix_item(cartDto.getFixItem())
//                            .build();
//
//                    cartRepository.save(cart);
//                    return cartDto;
//                })
//                .collect(Collectors.toList());
//    }
//    @Transactional()
//    @Override
//    public List<Cart> getCartItems() {
//        return cartRepository.findAll();
//    }
//}