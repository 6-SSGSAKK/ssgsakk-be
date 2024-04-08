package com.ssgsakk.ssgdotcom.vendor.infrastructure;

import com.ssgsakk.ssgdotcom.vendor.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
