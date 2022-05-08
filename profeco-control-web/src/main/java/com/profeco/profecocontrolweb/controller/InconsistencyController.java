package com.profeco.profecocontrolweb.controller;

import com.profeco.profecocontrolweb.model.Inconsistency;
import com.profeco.profecocontrolweb.model.Market;
import com.profeco.profecocontrolweb.service.InconsistencyService;
import com.profeco.profecocontrolweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InconsistencyController {

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/inconsistencies")
    public String  getInconsistencies(Model model){
        Market[] markets = marketService.getMarkets();
        model.addAttribute("marketList", markets);
        return "inconsistencies";
    }
}
