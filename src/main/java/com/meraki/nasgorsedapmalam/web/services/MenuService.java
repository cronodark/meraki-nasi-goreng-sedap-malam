package com.meraki.nasgorsedapmalam.web.services;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.models.Menu;

import java.util.List;

public interface MenuService {
    List<MenuDto> findAllMenus();
    Menu saveMenu(Menu menu);
    MenuDto findMenuById(int menuId);

    void updateMenu(MenuDto menu);

    void delete(int menuId);
}
