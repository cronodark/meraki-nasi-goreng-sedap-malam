package com.meraki.nasgorsedapmalam.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class GeneralController {

    private MenuService menuService;

    public GeneralController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<MenuDto> menus = menuService.findAllMenus();
        model.addAttribute("menus", menus);
        //return "menus-list";
        return "index";
    }

    //    detail menu
    @GetMapping("/menu/{menuId}")
    public String menuDetail(@PathVariable("menuId") int menuId, Model model){
        MenuDto menuDto = menuService.findMenuById(menuId);
        model.addAttribute("menu", menuDto);
        return "menu-detail";
    }

}
