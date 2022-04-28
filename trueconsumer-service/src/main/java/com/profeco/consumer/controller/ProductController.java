package com.profeco.consumer.controller;

import com.profeco.consumer.dto.ProductDTO;
import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.PushBuilder;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(@RequestParam(name = "name" , required = false) String name,
                                                      @RequestParam(name = "marketId", required = false) Long marketId) {
        List<Product> products;
        if(name == null && marketId == null){
            products = productService.findAll();
        } else {
            products = (name != null)?
                    productService.findByName(name)
                    :productService.findByMarket(Market.builder().id(marketId).build());
        }

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        //List<ProductDTO> productDTOList = products.stream().map( product -> product.toDTO()).
        //        collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id", required = true) Long id){
        Product product = productService.getProduct(id);
        if (product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product);
    }
}
