package com.profeco.consumer.service.market;

import com.profeco.consumer.entities.Market;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MarketService {
    public List<Market> findAll();
    public List<Market> findByName(String name);
    public Market findById(Long id);
    public Market createMarket(Market market, MultipartFile image);
    public Market updateMarket(Market market, MultipartFile image);
    public Market deleteMarket(Long id);
}
