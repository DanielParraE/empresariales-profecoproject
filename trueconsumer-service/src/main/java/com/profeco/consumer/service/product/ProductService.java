package com.profeco.consumer.service.product;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> findAll();
    List<Product> findByMarket(Market market);
    List<Product> findByName(String name);
}
