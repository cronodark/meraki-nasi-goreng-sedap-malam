package com.meraki.nasgorsedapmalam.web.controllers;

import com.meraki.nasgorsedapmalam.web.dto.RegistrationDto;
import com.meraki.nasgorsedapmalam.web.models.UserEntity;
import com.meraki.nasgorsedapmalam.web.security.CustomUserDetailsService;
import com.meraki.nasgorsedapmalam.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {

        UserEntity existingEmailUser = userService.findByEmail(user.getEmail());

        if (existingEmailUser != null && existingEmailUser.getEmail() != null && !existingEmailUser.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        UserEntity existingUsernameUser = userService.findByUsername(user.getUsername());
        if (existingUsernameUser != null && existingUsernameUser.getUsername() != null && !existingUsernameUser.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";

    }

}
