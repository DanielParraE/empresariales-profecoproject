package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MarketService {

    private final RestTemplate restTemplate;

    public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }

    public Product[] getPostsAsObject() {
        String url = "http://localhost:8091/products";
        return this.restTemplate.getForObject(url, Product[].class);
    }
}
