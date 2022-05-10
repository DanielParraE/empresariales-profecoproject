package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Product;
import com.profeco.trueconsumerweb.models.Wishlist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WishlistService {

    private final RestTemplate restTemplate;

    @Value("${backend.consumer-service.url}")
    private String baseUrl;

    public WishlistService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Wishlist[] getWishlistByConsumer(Long consumerId) {
        String url = baseUrl + "/wishlists?consumerId=" + consumerId;
        return this.restTemplate.getForObject(url, Wishlist[].class);
    }
}
