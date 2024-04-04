package com.ssgsakk.ssgdotcom.cart.infrastructure;

import com.ssgsakk.ssgdotcom.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser_UserSeq(Long userSeq);
    Integer countByUser_UserSeq(Long userSeq);

    @Query("SELECT c FROM Cart c JOIN c.optionAndStock os JOIN c.user u WHERE os.optionAndStockSeq = :optionAndStockSeq AND u.userSeq = :userSeq")
    Optional<Cart> checkCart(@Param("optionAndStockSeq") Long optionAndStockSeq, @Param("userSeq") Long userSeq);
}
