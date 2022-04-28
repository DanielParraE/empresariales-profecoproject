package com.profeco.consumer.service.market;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.repositories.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class DomainMarketService implements MarketService{

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    @Override
    public List<Market> findByName(String name) {
        return marketRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Market findById(Long id) {
        return marketRepository.findById(id).orElse(null);
    }
}
