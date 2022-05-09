package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {
    public List<Market> findByNameContainsIgnoreCase(String name);
    public Market findByRfc(String rfc);
}
