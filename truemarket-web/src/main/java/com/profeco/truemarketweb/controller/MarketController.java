package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.Market;
import com.profeco.truemarketweb.models.Product;
import com.profeco.truemarketweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MarketController {

    @Autowired
    private MarketService marketService;

}
