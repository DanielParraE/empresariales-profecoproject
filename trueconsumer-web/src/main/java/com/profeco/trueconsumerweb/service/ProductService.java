package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Value("${backend.consumer-service.url}")
    private String baseUrl;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getProductsPlainJSON(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }

    public Product[] getProductsAsObject() {
        String url = baseUrl + "/products";
        return this.restTemplate.getForObject(url, Product[].class);
    }

    public Product getProductAsObject(Long id) {
        String url = baseUrl + "/products/" + id;
        return this.restTemplate.getForObject(url, Product.class);
    }

    public Product[] getProductByName(String name) {
        String url = baseUrl + "/products?name=" + name;
        return this.restTemplate.getForObject(url, Product[].class);
    }

    public Product[] getProductsByMarketId(Long id) {
        String url = baseUrl + "/products?marketId=" + id;
        return this.restTemplate.getForObject(url, Product[].class);
    }

    public Product[] getProductsByMarketIdAndName(Long id, String name){
        String url = baseUrl+ "/markets/" + id + "/products?name=" + name;
        return this.restTemplate.getForObject(url, Product[].class);
    }
}
