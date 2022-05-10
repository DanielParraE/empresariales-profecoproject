package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.CustomOauth2User;
import com.profeco.truemarketweb.models.Person;
import com.profeco.truemarketweb.models.Product;
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

        Product product = productService.getProductAsObject(Long.valueOf(person.getMarketId()));
        model.addAttribute("product" , product);
        return "product-details-edit";
    }
}
