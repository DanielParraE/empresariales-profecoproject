package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Market;
import com.profeco.trueconsumerweb.models.MarketProduct;
import com.profeco.trueconsumerweb.models.MarketProductReview;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketProductReviewsService {

    private final RestTemplate restTemplate;

    public MarketProductReviewsService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public MarketProductReview[] getMarketProductReviews(Long marketProductId) {
        String url = "http://localhost:8091/marketproducts?marketProductId=" + marketProductId;
        return this.restTemplate.getForObject(url, MarketProductReview[].class);
    }
}
