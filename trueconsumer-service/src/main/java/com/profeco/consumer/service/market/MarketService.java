package com.profeco.consumer.service.market;

import com.profeco.consumer.entities.Market;

import java.util.List;

public interface MarketService {
    List<Market> findAll();
    List<Market> findByName(String name);

}
