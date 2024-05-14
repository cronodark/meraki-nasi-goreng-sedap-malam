package com.meraki.nasgorsedapmalam.web.controllers;

import java.util.ArrayList;
import java.util.List;

import com.meraki.nasgorsedapmalam.web.dto.RatingDto;
import com.meraki.nasgorsedapmalam.web.models.Rating;
import com.meraki.nasgorsedapmalam.web.models.UserEntity;
import com.meraki.nasgorsedapmalam.web.services.RatingService;
import com.meraki.nasgorsedapmalam.web.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class GeneralController {

    private MenuService menuService;
    private RatingService ratingService;
    private UserService userService;

    public GeneralController(MenuService menuService, RatingService ratingservice, UserService userService) {
        this.menuService = menuService;
        this.ratingService = ratingservice;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<MenuDto> menus = menuService.findAllMenusWithRatings();
        model.addAttribute("menus", menus);
        System.out.println("menus = " + menus);
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<MenuDto> menus = menuService.findAllMenus();
        model.addAttribute("menus", menus);
        return "menus-list";
    }

    //    detail menu
    @GetMapping("/menu/{menuId}")
    public String menuDetail(@PathVariable("menuId") int menuId, Model model){
        MenuDto menuDto = menuService.findMenuByIdWithRatings(menuId);
        Rating rating = new Rating();
        Rating rated = null;

        Authentication authed = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userService.findByUsername(authed.getName());

        if (currentUser!= null){
            rated = ratingService.findByIdMenuAndIdUser(menuId, currentUser.getId());
            System.out.println("log rated: " + rated);
        }


        model.addAttribute("menu", menuDto);
        model.addAttribute("rating", rating);
        model.addAttribute("rated", rated);

        return "menu-detail";
    }

}
