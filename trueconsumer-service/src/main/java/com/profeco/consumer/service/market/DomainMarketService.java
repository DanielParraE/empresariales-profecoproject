package com.profeco.consumer.service.market;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.entities.Market;
import com.profeco.consumer.repositories.MarketRepository;
import com.profeco.consumer.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class DomainMarketService implements MarketService{

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private StorageService storageService;

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

    @Override
    public Market createMarket(Market market, MultipartFile image) {
        Market marketDB = marketRepository.findByRfc(market.getRfc());
        if (marketDB != null) return marketDB;

        String url =  (image == null)?
                "http://localhost:8091/files/abc-market.png"
                : storageService.store(image);

        market.setImage(url);
        market.setStatus("CREATED");
        //marketDB.setEmail(consumer.getEmail());
        return marketRepository.save(market);
    }

    @Override
    public Market updateMarket(Market market, MultipartFile image) {
        Market marketDB = findById(market.getId());
        if (marketDB == null) {
            return null;
        }

        String url =  (image == null)?
                marketDB.getImage()
                : storageService.store(image);

        marketDB.setImage(url);

        marketDB.setName(market.getName());
        //marketDB.setAddressList(market.getAddressList());
        marketDB.setWebPage(market.getWebPage());
        //marketDB.setEmail(consumer.getEmail());
        return marketRepository.save(marketDB);
    }

    @Override
    public Market deleteMarket(Long id) {
        Market marketDB = findById(id);
        if (marketDB == null) return null;

        //consumerRepository.delete(consumerDB);
        marketDB.setStatus("DELETED");
        return marketRepository.save(marketDB);
    }
}
