package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.Product;
import com.profeco.truemarketweb.models.Product;
import com.profeco.truemarketweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public  String getPage(Authentication authentication, Model model){
        // reemplazar por el id del usuario de la sesión
        Product[] products = productService.getProductsByMarketId(1L);
        model.addAttribute("products" , products);
        return "products";
    }

    @GetMapping(value = "/products/{id}")
    public  String getPage(Authentication authentication, Model model, @PathVariable(value = "id", required = true) Long id){
        // reemplazar por el id del usuario de la sesión
        Product product = productService.getProductAsObject(1L);
        model.addAttribute("product" , product);
        return "product-details-edit";
    }
}
