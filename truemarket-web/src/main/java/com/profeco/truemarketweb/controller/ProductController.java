package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.*;
import com.profeco.truemarketweb.models.Product;
import com.profeco.truemarketweb.repository.PersonRepository;
import com.profeco.truemarketweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/products")
    public  String getPage(@AuthenticationPrincipal OAuth2User principal, Model model, @RequestParam(required = false) String name){

        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);

        Long id = Long.valueOf(person.getMarketId());

        Product[] products = name == null?
                productService.getProductsByMarketId(id)
                :productService.getProductsByMarketIdAndName(id, name);

        model.addAttribute("products" , products);
        return "products";
    }

    @GetMapping(value = "/products/{id}")
    public  String getPage(@AuthenticationPrincipal OAuth2User principal,
                           Model model, @PathVariable(value = "id", required = true) Long id){

        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);

        Product product = productService.getProductAsObject(id);

        Long marketId = Long.valueOf(person.getMarketId());

        Optional<MarketProduct> market = product.getMarketProductList()
                .stream()
                .filter(marketProduct -> marketProduct.getMarket().getId() == marketId).findFirst();

        model.addAttribute("product" , product);
        model.addAttribute("marketProduct" , market.get());
        return "product-details-edit";
    }

    @GetMapping(value = "/products/{productId}/reviews")
    public String getProductReviews(@AuthenticationPrincipal OAuth2User principal,
                                    @PathVariable(value = "productId", required = true) Long productId, Model model){
        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);
        Long marketId = Long.valueOf(person.getMarketId());

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
