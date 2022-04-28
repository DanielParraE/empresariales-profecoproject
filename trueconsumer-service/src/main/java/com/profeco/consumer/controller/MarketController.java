package com.profeco.consumer.controller;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.service.market.MarketService;
import com.profeco.consumer.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/markets")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Market>> findAll(@RequestParam(name = "name" , required = false) String name){
        List<Market> marketList = name == null || name.isEmpty() ?
                marketService.findAll()
                :marketService.findByName(name);

        if (marketList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(marketList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Market> getMarket(@PathVariable(value = "id", required = true) Long id){
        Market market = marketService.findById(id);
        if (market == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(market);
    }

    @GetMapping(value = "/{id}/products")
    public ResponseEntity<List<Product>> getMarketProductsByMarketId(@PathVariable(value = "id", required = true) Long id,
                                                       @RequestParam(value = "name", required = false) String name){
        Market market = Market.builder().id(id).build();
        List<Product> products = (name == null)?
                productService.findByMarket(market)
                :productService.findByMarketAndName(market , name);

        if (products.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(products);
    }
}
