package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Market;
import com.profeco.trueconsumerweb.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketService {

    private final RestTemplate restTemplate;

    public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Market[] getMarketsAsObject() {
        String url = "http://localhost:8091/markets";
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market[] getMarketsByName(String  name){
        String url = "http://localhost:8091/markets?name=" +name;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market getMarket(Long id) {
        String url = "http://localhost:8091/markets/" + id;
        return this.restTemplate.getForObject(url, Market.class);
    }
}
