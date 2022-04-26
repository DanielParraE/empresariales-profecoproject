package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/markets")
    public String marketsPage(Model model){
        System.out.println(marketService.getPostsPlainJSON("https://localhost:8091/markets"));
        return "markets";
    }
}
