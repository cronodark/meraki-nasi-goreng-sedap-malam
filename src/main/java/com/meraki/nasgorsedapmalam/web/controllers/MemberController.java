package com.meraki.nasgorsedapmalam.web.controllers;

import com.meraki.nasgorsedapmalam.web.models.Menu;
import com.meraki.nasgorsedapmalam.web.models.Rating;
import com.meraki.nasgorsedapmalam.web.models.UserEntity;
import com.meraki.nasgorsedapmalam.web.services.RatingService;
import com.meraki.nasgorsedapmalam.web.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.meraki.nasgorsedapmalam.web.repository.MenuRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private UserService userService;
    private RatingService ratingService;

    public MemberController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @PostMapping("/rating")
    public String storeRating(@ModelAttribute("rating") Rating rating) {

        Authentication authed = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userService.findByUsername(authed.getName());

        rating.setIdUser(currentUser.getId());

        ratingService.addRating(rating);

        return "redirect:/menu/"+rating.getIdMenu();
    }
}
