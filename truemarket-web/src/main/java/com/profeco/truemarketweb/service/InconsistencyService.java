package com.profeco.truemarketweb.service;

import com.profeco.truemarketweb.models.Inconsistency;
import com.profeco.truemarketweb.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InconsistencyService {

    @Value("${backend.consumer-service.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public InconsistencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Inconsistency[] getInconsistencyByMarketProduct(Long marketProductId) {
        String url = baseUrl + "/inconsistencies?marketProductId=" + marketProductId;
        return this.restTemplate.getForObject(url, Inconsistency[].class);
    }
}
