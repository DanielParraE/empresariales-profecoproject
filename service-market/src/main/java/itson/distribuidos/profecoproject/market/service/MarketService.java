package itson.distribuidos.profecoproject.market.service;

import itson.distribuidos.profecoproject.market.entities.Market;

import java.util.List;

public interface MarketService {
    public List<Market> listAllMarket();
    public Market getMarket(Long id);
    public Market createMarket(Market consumer);
    public Market updateMarket(Market consumer);
    public boolean deleteMarket(Long id);
}
