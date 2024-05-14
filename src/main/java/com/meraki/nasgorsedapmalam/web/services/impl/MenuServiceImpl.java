package com.meraki.nasgorsedapmalam.web.services.impl;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.models.Menu;
import com.meraki.nasgorsedapmalam.web.models.Rating;
import com.meraki.nasgorsedapmalam.web.repository.MenuRepository;
import com.meraki.nasgorsedapmalam.web.repository.RatingRepository;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService { //implementasi dari service
    private MenuRepository menuRepository;
    private RatingRepository ratingRepository;

    //constructor
    public MenuServiceImpl(MenuRepository menuRepository, RatingRepository ratingRepository) {
        this.menuRepository = menuRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<MenuDto> findAllMenus() {
        List<Menu> menus = menuRepository.findAll(); //mencari seluruh menu
        return menus.stream().map((menu) -> mapToMenuDto(menu)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> findAllMenusWithRatings() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDto> menuDtos = new ArrayList<>();

        for (Menu menu : menus) {
            List<Rating> ratings = ratingRepository.findByIdMenu(menu.getId());
            System.out.println(ratings);
            double ratingValue = 0.0;
            int ratingCount = 0;

            if (ratings.size() > 0) {
                for (Rating rating : ratings) {
                    ratingValue += rating.getRating();
                }
                ratingCount = ratings.size();
                ratingValue = ratingValue / ratingCount;
            }

            MenuDto menuDto = mapToMenuDto(menu);
            menuDto.setRatingValue(ratingValue);
            menuDto.setRatingCount(ratingCount);
            menuDtos.add(menuDto);
        }

        return menuDtos;
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
    public MenuDto findMenuByIdWithRatings(int menuId) {
        Menu menu = menuRepository.findById(menuId).get();

        NumberFormat formatter = new DecimalFormat("#0.00");

        List<Rating> ratings = ratingRepository.findByIdMenu(menuId);
        double ratingValue = 0.0;
        int ratingCount = 0;

        if (ratings.size() > 0 || ratings != null) {
            for (Rating rating : ratings) {
                ratingValue += rating.getRating();
            }
            ratingCount = ratings.size();
            ratingValue = ratingValue / ratingCount;
        }
        else{
            ratingValue = 0;
            ratingCount = 0;
        }


        MenuDto menuDto = mapToMenuDto(menu);
        menuDto.setRatingValue(ratingValue);
        menuDto.setRatingCount(ratingCount);

        System.out.println("rating: " + menuDto);

        return menuDto;
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
                .ratingValue(0)
                .ratingCount(0)
                .createdOn(menu.getCreatedOn())
                .updatedOn(menu.getUpdatedOn())
                .build();

        return menuDto;
    }

}
