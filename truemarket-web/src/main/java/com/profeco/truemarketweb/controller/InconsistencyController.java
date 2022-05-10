package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.*;
import com.profeco.truemarketweb.repository.PersonRepository;
import com.profeco.truemarketweb.service.InconsistencyService;
import com.profeco.truemarketweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/{id}/inconsistencies")
    public String getInconsistenciesByProduct(@AuthenticationPrincipal OAuth2User principal, Model model, @PathVariable Long id){
        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);
        Long marketId = Long.valueOf(person.getMarketId());

        Product product = productService.getProductAsObject(id);
        Optional<MarketProduct> market = product.getMarketProductList()
                .stream()
                .filter(marketProduct -> marketProduct.getMarket().getId() == marketId).findFirst();

        Inconsistency[] inconsistencyList = inconsistencyService.getInconsistencyByMarketProduct(market.get().getId());
        model.addAttribute("product", product);
        model.addAttribute("inconsistencies", inconsistencyList);
        return "market-product-inconsistencies";
    }
}
