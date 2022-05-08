package com.profeco.profecocontrolweb.service;

import com.profeco.profecocontrolweb.model.Inconsistency;
import com.profeco.profecocontrolweb.model.Market;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketService {

    private RestTemplate restTemplate;

    @Value("${backend.profeco-service.url}")
    private String baseUrl;

    public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Market[] getMarkets(){
        String url = baseUrl + "/markets";
        return this.restTemplate.getForObject(url, Market[].class);
    }
}
