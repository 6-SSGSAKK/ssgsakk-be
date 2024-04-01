package com.ssgsakk.ssgdotcom.purchase.infrastructure;

import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

}