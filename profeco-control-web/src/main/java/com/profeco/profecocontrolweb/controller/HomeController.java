package com.profeco.profecocontrolweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String getHome(){
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLogin(){
        return "login";
    }
}
