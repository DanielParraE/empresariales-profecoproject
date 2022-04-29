package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.Market;
import com.profeco.trueconsumerweb.models.Product;
import com.profeco.trueconsumerweb.service.MarketService;
import com.profeco.trueconsumerweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MarketController {

    @Autowired
    private MarketService marketService;

    @Autowired
    private ProductService productService;

    @GetMapping("/markets")
    public String marketsPage(@RequestParam(value = "name", required = false) String name, Model model){
        Market[] marketList;
        marketList = name == null ?
                marketService.getMarketsAsObject()
                :marketService.getMarketsByName(name);
        model.addAttribute("markets", marketList);
        return "markets";
    }

    @GetMapping(value = "/markets/{id}/products")
    public String getMarketDetailsPage(@PathVariable(value = "id", required = true) Long id,
                                       @RequestParam(value = "name", required = false) String name, Model model){
        Product[] products;

        products = (name == null) ?
                productService.getProductsByMarketId(id)
                        :productService.getProductsByMarketIdAndName(id, name);

            Market market = marketService.getMarket(id);
            model.addAttribute("market", market);
            model.addAttribute("products", products);
            return "market-details";
    }

    @GetMapping(value = "/markets/{id}/reviews")
    public String getMarketReviews(@PathVariable(value = "id", required = true) Long id, Model model){
        Market market = marketService.getMarket(id);

        model.addAttribute("market", market);
        return "market-reviews";
    }

    @GetMapping(value = "/markets/{marketId}/products/{productId}/reviews")
    public String getMarketDetailsPage(@PathVariable(value = "id", required = true) Long marketId,
                                       @PathVariable(value = "name", required = true) Long productId, Model model){
        Product[] products;

//        products = productService.getProductsByMarketIdAndName(marketId, productId);
//
//        Market market = marketService.getMarket(id);
//        model.addAttribute("market", market);
//        model.addAttribute("products", products);
        return "market-details";
    }
}
