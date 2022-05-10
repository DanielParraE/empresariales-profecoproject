package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Inconsistency;
import com.profeco.consumer.entities.MarketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InconsistencyRepository extends JpaRepository<Inconsistency, Long> {
    List<Inconsistency> findByMarketProduct(MarketProduct marketProduct);
}
