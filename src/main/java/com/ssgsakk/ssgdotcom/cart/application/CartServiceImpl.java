package com.ssgsakk.ssgdotcom.cart.application;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import com.ssgsakk.ssgdotcom.cart.dto.CartDto;
import com.ssgsakk.ssgdotcom.cart.dto.CartInfoDto;
import com.ssgsakk.ssgdotcom.cart.infrastructure.CartRepository;
import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.infrastructure.ProductContentsRepository;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.member.infrastructure.MemberRepository;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import com.ssgsakk.ssgdotcom.option.infrastructure.OptionAndStockRepository;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import com.ssgsakk.ssgdotcom.product.infrastructure.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final OptionAndStockRepository optionAndStockRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ProductContentsRepository productContentsRepository;

    @Transactional
    @Override
    public String addCart(CartDto cartDto, String uuid) {
        Cart existingCart = cartRepository.checkCart(cartDto.getOptionAndStockSeq(), checkUser(uuid).getUserSeq())
                .orElse(null);

        if (existingCart != null) {
            int updatedQuantity = existingCart.getQuantity() + 1;
            updateQuantityInEntity(existingCart, updatedQuantity);
            return String.format("한번 더 담으셨네요!\n담긴 수량이 %d개가 되었습니다.", updatedQuantity);
        } else {
            cartRepository.save(convertToEntity(cartDto, uuid));
            return("success");
        }
    }

    @Transactional
    @Override
    public CartInfoDto getCart(Long cartSeq, String uuid) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, uuid);
        return toCartInfoDto(cart);
    }

    @Transactional
    @Override
    public void deleteCart(Long cartSeq, String uuid) {
        checkUserCart(checkcart(cartSeq), uuid);
        cartRepository.deleteById(cartSeq);
    }

    @Transactional
    @Override
    public void updateQuantity(Long cartSeq, Integer quantity, String uuid) {
        Cart cart = checkcart(cartSeq) ;
        checkUserCart(cart, uuid);
        updateQuantityInEntity(cart,quantity);
    }

    @Transactional
    @Override
    public void updateOption(Long cartSeq, Long optionAndStockSeq, String uuid) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, uuid);
        updateOptionInEntity(cart,optionAndStockSeq);
    }

    @Transactional
    @Override
    public Integer getCartCount(String uuid) {
        return cartRepository.countByUser_UserSeq(checkUser(uuid).getUserSeq());
    }

    @Transactional
    @Override
    public void updateCartPin(Long cartSeq, Integer fixItem, String uuid) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, uuid);
        updateCartPinInEntity(cart,fixItem);
    }

    @Transactional
    @Override
    public void updateCheckbox(Long cartSeq, Integer checkbox, String uuid) {
        Cart cart = checkcart(cartSeq);
        checkUserCart(cart, uuid);
        updateCartCheckInEntity(cart,checkbox);
    }

    @Override
    public void updateAllCheck(Integer checkbox, String uuid) {
        List<Cart> carts = cartRepository.findByUser_UserSeq(checkUser(uuid).getUserSeq());
        for (Cart cart : carts) {
            updateCartCheckInEntity(cart, checkbox);
        }
    }

    @Transactional
    @Override
    public List<CartDto> getCartList(String uuid) {
        return convertToDto(cartRepository.findByUser_UserSeq(checkUser(uuid).getUserSeq()));
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
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_CART));
    }

    private User checkUser(String uuid) {
        return memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new BusinessException(ErrorCode.NO_EXIST_MEMBERS));
    }
    private void checkUserCart(Cart cart, String uuid) {
        User user = cart.getUser();

        if (!user.getUuid().equals(uuid)) {
            throw new BusinessException(ErrorCode.JWT_VALID_FAILED);
        }
    }

    private Cart convertToEntity(CartDto cartDto, String uuid) {
        OptionAndStock optionAndStock = optionAndStockRepository.findById(cartDto.getOptionAndStockSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_OPTION));
        Product product = productRepository.findById(cartDto.getProductSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_PRODUCT));

        return Cart.builder()
                .user(checkUser(uuid))
                .optionAndStock(optionAndStock)
                .product(product)
                .quantity(cartDto.getQuantity())
                .checkbox(cartDto.getCheckbox())
                .fixItem(cartDto.getFixItem())
                .build();
    }
    private void updateQuantityInEntity(Cart cart, Integer quantity) {
        Cart updatedCart = Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(quantity)
                .checkbox(cart.getCheckbox())
                .fixItem(cart.getFixItem())
                .build();
        cartRepository.save(updatedCart);
    }

    private void updateOptionInEntity(Cart cart, Long optionAndStockSeq) {
        Cart updatedCart = Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(optionAndStockRepository.findById(optionAndStockSeq)
                        .orElseThrow(() -> new BusinessException(ErrorCode.CANNOT_FOUND_OPTION)))
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(cart.getCheckbox())
                .fixItem(cart.getFixItem())
                .build();
        cartRepository.save(updatedCart);
    }

    private void updateCartPinInEntity(Cart cart, Integer fixItem) {
        Cart updatedCart = Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(cart.getCheckbox())
                .fixItem(fixItem)
                .build();
        cartRepository.save(updatedCart);
    }

    private void updateCartCheckInEntity(Cart cart, Integer checkbox) {
        Cart updatedCart = Cart.builder()
                .cartSeq(cart.getCartSeq())
                .user(cart.getUser())
                .optionAndStock(cart.getOptionAndStock())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .checkbox(checkbox)
                .fixItem(cart.getFixItem())
                .build();
        cartRepository.save(updatedCart);
    }

    private CartInfoDto toCartInfoDto(Cart cart) {
        String option = (cart.getOptionAndStock().getColor() != null ?
                cart.getOptionAndStock().getColor().getColorData() + " " : "") +
                (cart.getOptionAndStock().getSize() != null ?
                        cart.getOptionAndStock().getSize().getSizeData()  + " ": "") +
                (cart.getOptionAndStock().getCustomizationOption() != null ?
                        cart.getOptionAndStock().getCustomizationOption().getCustomizationData() : "");
        return CartInfoDto.builder()
                .productSeq(cart.getProduct().getProductSeq())
                .productOption(option)
                .productVendor(cart.getProduct().getVendor().getVendorName())
                .productPrice(cart.getProduct().getProductPrice())
                .productName(cart.getProduct().getProductName())
                .quantity(cart.getQuantity())
                .productImage(getContents(cart.getProduct().getProductSeq()))
                .discountPercent(cart.getProduct().getDiscountPercent())
                .build();
    }
    private String getContents(Long productSeq) {
        List<ProductContents> contents = productContentsRepository.findByProduct_ProductSeq(productSeq);

        if (contents != null) {
            for (ProductContents content : contents) {
                if (content.getPriority() == 1) {
                    return content.getContents() != null ? content.getContents().getContentUrl() : null;
                }
            }
        }
        return null;
    }

}