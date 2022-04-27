package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findByNameContainsIgnoreCase(String name);
    List<Market> findAll();
}
