package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.Market;
import com.profeco.trueconsumerweb.models.MarketProduct;
import com.profeco.trueconsumerweb.models.Product;
import com.profeco.trueconsumerweb.service.MarketService;
import com.profeco.trueconsumerweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/products")
    public String getPage(@RequestParam(value = "name", required = false) String name, Model model){
        Product[] products;
        products = (name == null)?
                productService.getProductsAsObject()
                : productService.getProductByName(name);



        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping(value = "/products/{id}")
    public String getProductDetailsPage(@PathVariable(value = "id", required = true) Long id, Model model){

            Product product = productService.getProductAsObject(id);
            model.addAttribute("product", product);
            return "product-details";

    }

    @GetMapping(value = "/products/{productId}/markets/{marketId}/reviews")
    public String getMarketProductReviews(@PathVariable(value = "productId", required = true) Long productId,
                                          @PathVariable(value = "marketId", required = true) Long marketId,
                                          Model model){
        Product product = productService.getProductAsObject(productId);
        Optional<MarketProduct> market = product.getMarketProductList()
                            .stream()
                            .filter(marketProduct -> marketProduct.getMarket().getId() == marketId).findFirst();

        if (market.isPresent()) {
            model.addAttribute("product", product);
            model.addAttribute("market", market.get().getMarket());
            model.addAttribute("reviews", market.get().getReviews());
            return "market-product-reviews";
        }

        return "";
    }
}
