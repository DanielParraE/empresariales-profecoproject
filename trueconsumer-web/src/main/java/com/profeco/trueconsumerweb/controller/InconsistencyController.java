package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.*;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import com.profeco.trueconsumerweb.service.InconsistencyService;
import com.profeco.trueconsumerweb.service.MarketService;
import com.profeco.trueconsumerweb.service.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @Autowired
    private MarketService marketService;
    @Autowired
    private ProductService productService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/markets/{marketId}/products/{productId}/inconsistencies")
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
            model.addAttribute("publishedPrice", market.get().getPrice());
            model.addAttribute("inconsistency", new Inconsistency());
            model.addAttribute("marketProductId", market.get().getId());
            return "report-product";
        }

        return "";
    }

    @PostMapping(value = "/inconsistencies",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String createInconsistency(@AuthenticationPrincipal OAuth2User principal,
                                      @RequestPart Inconsistency inconsistency,
                                      @RequestPart(required = false) MultipartFile file) throws IOException {

        byte[] bytes = (file == null) ? null: file.getBytes();

        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);

        inconsistency.setAuthor(Consumer.builder().id(Long.valueOf(person.getConsumerId())).build());
        Inconsistency inconsistency1 = inconsistencyService.postInconsistency(inconsistency, bytes, RandomStringUtils.randomAlphanumeric(8));

        return "index";
    }
}
