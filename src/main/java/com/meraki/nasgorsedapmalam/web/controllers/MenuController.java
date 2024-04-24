package com.meraki.nasgorsedapmalam.web.controllers;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenuController {
    private MenuService menuService;
    public int radit;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String listMenu(Model model){
        List<MenuDto> menus = menuService.findAllMenus();
        model.addAttribute("menus", menus);
        return "menus-list";
    }

}
