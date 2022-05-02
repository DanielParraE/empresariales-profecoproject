package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.MarketProduct;
import com.profeco.trueconsumerweb.models.Product;
import com.profeco.trueconsumerweb.service.InconsistencyService;
import com.profeco.trueconsumerweb.service.MarketService;
import com.profeco.trueconsumerweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @Autowired
    private MarketService marketService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products/{productId}/markets/{marketId}/inconsistencies")
    public String postInconsistency(@PathVariable(value = "productId", required = true) Long productId,
                                          @PathVariable(value = "marketId", required = true) Long marketId,
                                          Model model){
        Product product = productService.getProductAsObject(productId);
        Optional<MarketProduct> market = product.getMarketProductList()
                .stream()
                .filter(marketProduct -> marketProduct.getMarket().getId() == marketId).findFirst();

        if (market.isPresent()) {
            model.addAttribute("product", product);
            model.addAttribute("market", market.get().getMarket());
            return "report-product";
        }

        return "";
    }
}
