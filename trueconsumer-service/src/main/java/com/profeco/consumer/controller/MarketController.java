package com.profeco.consumer.controller;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.service.market.MarketService;
import com.profeco.consumer.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Market> createMarket(@RequestPart Market market,
                                                   @RequestPart(required = false) MultipartFile file,
                                                   BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));

        Market marketCreated = marketService.createMarket(market, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(marketCreated);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Market> updateMarket(@PathVariable(value = "id") Long id,
                                                   @RequestPart Market market,
                                                   @RequestPart(required = false) MultipartFile file) {
        market.setId(id);

        Market marketDB = marketService.updateMarket(market, file);
        if (marketDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marketDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMarket(@PathVariable(value = "id") Long id) {
        Market marketDeleted = marketService.deleteMarket(id);
        if (marketDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("[ DELETED ]");
    }
}
