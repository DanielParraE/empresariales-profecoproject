package com.profeco.consumer.service;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainProductService implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByMarket(Market market) {
        return productRepository.findByMarketProductListMarket(market);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }


}
