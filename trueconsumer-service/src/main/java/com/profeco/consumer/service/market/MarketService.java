package com.profeco.consumer.service.market;

import com.profeco.consumer.entities.Market;

import java.util.List;

public interface MarketService {
    public List<Market> findAll();
    public List<Market> findByName(String name);
    public Market findById(Long id);
}
