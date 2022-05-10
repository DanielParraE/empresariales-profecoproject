package com.profeco.truemarketweb.controller;

import com.profeco.truemarketweb.models.CustomOauth2User;
import com.profeco.truemarketweb.models.Market;
import com.profeco.truemarketweb.models.Person;
import com.profeco.truemarketweb.repository.PersonRepository;
import com.profeco.truemarketweb.service.MarketService;
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

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/login")
    public String login(){

        return "login";
    }

    @GetMapping(value = "/")
    public String home (@CurrentSecurityContext(expression="authentication") Authentication authentication){


        return "index";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model){
        model.addAttribute("market", new Market());
        return "signup";
    }


    @GetMapping(value = "/profile")
    public String profile(@AuthenticationPrincipal OAuth2User principal, Model model){

        String uid;
        if (principal == null ){
            UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            uid = userDetails.getUsername();
            model.addAttribute("isAuthenticatedByEmail", true);
        } else {
            uid = ((CustomOauth2User) principal).getEmail();
            model.addAttribute("isAuthenticatedByEmail", false);
        }

        Person person = personRepo.findByUID(uid);

        Market market = marketService.getMarket(Long.valueOf(person.getMarketId()));
        model.addAttribute("market", market);
        return "profile";

    }
}
