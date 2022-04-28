package com.profeco.truemarketweb.service;

import com.profeco.truemarketweb.models.Market;
import com.profeco.truemarketweb.models.Market;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketService {
    private final RestTemplate restTemplate;

    public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public Market[] getMarkets() {
        String url = "http://localhost:8091/markets";
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market getMarket(Long id) {
        String url = "http://localhost:8091/markets/" + id;
        return this.restTemplate.getForObject(url, Market.class);
    }

    public Market[] getMarketByName(String name) {
        String url = "http://localhost:8091/Markets?name=" + name;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market[] getMarketsByMarketId(Long id) {
        String url = "http://localhost:8091/Markets?marketId=" + id;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market[] getMarketsByMarketIdAndName(Long id, String name){
        String url = "http://localhost:8091/markets/" + id + "/Markets?name=" + name;
        return this.restTemplate.getForObject(url, Market[].class);
    }
}
