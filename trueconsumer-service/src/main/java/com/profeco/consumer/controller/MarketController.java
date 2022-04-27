package com.profeco.consumer.controller;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.service.market.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/markets")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping
    public ResponseEntity<List<Market>> findAll(@RequestParam(value = "name", required = false) String name){
        List<Market> marketList;

        marketList = (name == null || name.isEmpty()) ?
                marketService.findAll():
                marketService.findByName(name);

        if (marketList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(marketList);
    }
}
