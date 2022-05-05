package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.Consumer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
        model.addAttribute("consumer", new Consumer());
        return "signup";
    }

    @GetMapping(value = "/profile")
    public String profile(Model model){
        model.addAttribute("consumer", new Consumer());
        return "signup";
    }
}
