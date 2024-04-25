package com.meraki.nasgorsedapmalam.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthenticationController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/login-auth")
    public String loginAuth(@RequestParam("username") String username, @RequestParam("password") String password) {

        return "";
    }

}
