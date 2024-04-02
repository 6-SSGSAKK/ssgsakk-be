package com.ssgsakk.ssgdotcom.cart.infrastructure;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
