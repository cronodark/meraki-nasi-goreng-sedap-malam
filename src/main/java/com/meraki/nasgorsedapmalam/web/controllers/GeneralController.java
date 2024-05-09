package com.meraki.nasgorsedapmalam.web.controllers;

import java.util.ArrayList;
import java.util.List;

import com.meraki.nasgorsedapmalam.web.dto.RatingDto;
import com.meraki.nasgorsedapmalam.web.models.Rating;
import com.meraki.nasgorsedapmalam.web.services.RatingService;
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

    public GeneralController(MenuService menuService, RatingService ratingservice) {
        this.menuService = menuService;
        this.ratingService = ratingservice;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<MenuDto> menus = menuService.findAllMenus();
        List<Rating> ratings = ratingService.findAllRatings();
        List<RatingDto> allRating = new ArrayList<>();

        float tempRating = 0;
        int totalRatingCount = 0;
        for (int i = 0; i < menus.size(); i++) {
            for (int j = 0; j < ratings.size(); j++){
                if (menus.get(i).getId() == ratings.get(j).getIdMenu()){
                    tempRating += ratings.get(j).getRating();
                    totalRatingCount += 1;
                }
            }
            tempRating = tempRating / totalRatingCount;
            RatingDto createRating = new RatingDto(menus.get(i).getId(), tempRating, totalRatingCount);
            allRating.add(createRating);
            tempRating = 0;
            totalRatingCount = 0;
        }
        model.addAttribute("ratings", allRating);
        model.addAttribute("menus", menus);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
        MenuDto menuDto = menuService.findMenuById(menuId);
        Rating rating = new Rating();

        model.addAttribute("menu", menuDto);
        model.addAttribute("rating", rating);

        return "menu-detail";
    }

}
