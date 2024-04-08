package com.ssgsakk.ssgdotcom.option.infrastructure;

import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionAndStockRepository extends JpaRepository<OptionAndStock, Long> {
    List<OptionAndStock> findByProductSeq(Long productSeq);
}
