package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByMarketProductListMarket(Market market);
    public List<Product> findByNameContainingIgnoreCase(String name);
    public List<Product> findByMarketProductListMarketAndNameContainingIgnoreCase(Market market, String name);
}
