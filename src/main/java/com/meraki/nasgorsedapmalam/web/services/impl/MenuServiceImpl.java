package com.meraki.nasgorsedapmalam.web.services.impl;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.models.Menu;
import com.meraki.nasgorsedapmalam.web.repository.MenuRepository;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService { //implementasi dari service
    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository; //constructor
    }

    @Override
    public List<MenuDto> findAllMenus() {
        List<Menu> menus = menuRepository.findAll(); //mencari seluruh menu
        return menus.stream().map((menu) -> mapToMenuDto(menu)).collect(Collectors.toList());
    }

    @Override
    public Menu saveMenu(Menu menu){
        return menuRepository.save(menu);
    }

    @Override
    public MenuDto findMenuById(int menuId) {
        Menu menu = menuRepository.findById(menuId).get();
        return mapToMenuDto(menu);
    }

    @Override
    public void updateMenu(MenuDto menuDto) {
        Menu menu = mapToMenu(menuDto);
        menuRepository.save(menu);
    }

    @Override
    public void delete(int menuId) {
        menuRepository.deleteById(menuId);
    }

    private Menu mapToMenu(MenuDto menu) {
        Menu menuDto = Menu.builder()
                .id(menu.getId())
                .nama(menu.getNama())
                .photoUrl(menu.getPhotoUrl())
                .harga(menu.getHarga())
                .deskripsi(menu.getDeskripsi())
                .createdOn(menu.getCreatedOn())
                .updatedOn(menu.getUpdatedOn())
                .build();

        return menuDto;
    }

    private MenuDto mapToMenuDto(Menu menu){ //menerjemahkan dari class menu ke class menuDto
        MenuDto menuDto = MenuDto.builder()
                .id(menu.getId())
                .nama(menu.getNama())
                .photoUrl(menu.getPhotoUrl())
                .harga(menu.getHarga())
                .deskripsi(menu.getDeskripsi())
                .createdOn(menu.getCreatedOn())
                .updatedOn(menu.getUpdatedOn())
                .build();

        return menuDto;
    }

}
