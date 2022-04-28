package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.service.InconsistencyService;
import com.profeco.trueconsumerweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/inconsistencies/{marketId}")
    private String getPage(@PathVariable(value = "marketId", required = true) Long marketId, Model model){
        marketService.getMarketsAsObject();
        return "inconsistency-create";
    }
}
