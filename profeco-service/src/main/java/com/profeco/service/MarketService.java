package com.profeco.service;

import com.profeco.entities.Market;

import java.util.List;

public interface MarketService {
    public List<Market> listAllMarket();
    public Market getMarket(Long id);
    public Market createMarket(Market consumer);
    public Market updateMarket(Market consumer);
    public boolean deleteMarket(Long id);
}
