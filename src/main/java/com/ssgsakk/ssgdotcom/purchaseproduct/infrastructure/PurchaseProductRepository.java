package com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Long> {
}
