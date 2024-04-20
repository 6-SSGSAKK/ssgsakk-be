package com.ssgsakk.ssgdotcom.purchase.infrastructure;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    Optional<Purchase> findByPurchaseSeq(Long purchaseSeq);

}