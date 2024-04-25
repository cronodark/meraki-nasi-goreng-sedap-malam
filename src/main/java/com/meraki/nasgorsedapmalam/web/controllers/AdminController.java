package com.meraki.nasgorsedapmalam.web.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.models.Menu;
import com.meraki.nasgorsedapmalam.web.services.MenuService;


@Controller
public class AdminController {
    
    private MenuService menuService;

    public AdminController(MenuService menuService) {
        this.menuService = menuService;
    }

//    tambah menu
    @GetMapping("/tambah-menu")
    public String addMenu(Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        return "menu-tambah";
    }

    @PostMapping("/tambah-menu")
    public String storeMenu(@ModelAttribute("menu") Menu menu) {
        menuService.saveMenu(menu);
        return "redirect:/";
    }



//    edit menu
    @GetMapping("/menu/{menuId}/edit")
    public String editMenuForm(Model model, @PathVariable("menuId") int idMenu) {

        MenuDto menu = menuService.findMenuById(idMenu);

        model.addAttribute("menu", menu);

        return "menu-edit";
    }

    @PostMapping("/menu/{menuId}/edit")
    public String updateMenu(@PathVariable("menuId") int menuId, @ModelAttribute("menu") MenuDto menu) {

        menu.setId(menuId);
        menuService.updateMenu(menu);
        return "redirect:/";
    }

//    delete menu
    @GetMapping("/menu/{menuId}/delete")
    public String deleteMenu(@PathVariable("menuId") int menuId){
        menuService.delete(menuId);
        return "redirect:/";
    }
}
