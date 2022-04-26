package com.profeco.market.service;

import com.profeco.market.entities.Market;
import com.profeco.market.repositories.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainMarketService implements MarketService {

    private final MarketRepository marketRepository;

    @Override
    public List<Market> listAllMarket() {
        return marketRepository.findAll();
    }

    @Override
    public Market getMarket(Long id) {
        return marketRepository.findById(id).orElse(null);
    }

    @Override
    public Market createMarket(Market market) {
        if (market.getImage() == null || market.getImage().isEmpty())
            market.setImage("abc-market.png");

        return marketRepository.save(market);
    }

    @Override
    public Market updateMarket(Market market) {
        Market marketDB = getMarket(market.getId());
        if (marketDB == null) {
            return null;
        }
        marketDB.setName(market.getName());
        marketDB.setRfc(market.getRfc());
        marketDB.setWebPage(market.getWebPage());
        return marketRepository.save(marketDB);
    }

    @Override
    public boolean deleteMarket(Long id) {
        Market marketDB = getMarket(id);
        if (marketDB == null) {
            return false;
        }
        marketRepository.delete(marketDB);
        return true;
    }
}
