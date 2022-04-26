package com.profeco.trueconsumerweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profeco.trueconsumerweb.models.Product;
import com.profeco.trueconsumerweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/products")
    public String getPage(Model model){
        Product[] products = marketService.getPostsAsObject();
        //List<Product> productList = new ObjectMapper().readValue(json, new TypeReference<List<Product>>(){});
        model.addAttribute("products", products);
        return "products";
    }
}
