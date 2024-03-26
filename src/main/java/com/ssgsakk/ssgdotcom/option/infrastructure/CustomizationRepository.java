package com.ssgsakk.ssgdotcom.option.infrastructure;

import com.ssgsakk.ssgdotcom.option.domain.CustomizationOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizationRepository extends JpaRepository<CustomizationOption, Long> {
}
