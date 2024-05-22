package com.meraki.nasgorsedapmalam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meraki.nasgorsedapmalam.web.dto.MenuDto;
import com.meraki.nasgorsedapmalam.web.models.Menu;
import com.meraki.nasgorsedapmalam.web.services.MenuService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;


@Controller
public class AdminController {

    @Autowired
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
    public String storeMenu(@ModelAttribute("menu") Menu menu, @RequestParam("gambarfile") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        menu.setPhotoUrl(fileName);

        Menu savedMenu = menuService.saveMenu(menu);

        String uploadDir = "src/main/resources/static/images/menu/" + savedMenu.getId();

        Path uploadPath =  Paths.get(uploadDir);

        if (!Files.exists(uploadPath)){ //memeriksa apakah directory sudah ada
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream();) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Tidak bisa menyimpan gambar yang dikirim: " + fileName);
        }

        return "redirect:/menu";
    }


//    edit menu
    @GetMapping("/menu/{menuId}/edit")
    public String editMenuForm(Model model, @PathVariable("menuId") int idMenu) {

        MenuDto menu = menuService.findMenuById(idMenu);

        model.addAttribute("menu", menu);
        return "menu-edit";
    }

    @PostMapping("/menu/{menuId}/edit")
    public String updateMenu(@PathVariable("menuId") int menuId, @ModelAttribute("menu") MenuDto menu , @RequestParam("newPhoto") MultipartFile newPhoto) throws IOException {

        if (!newPhoto.isEmpty()){
            MenuDto existingMenu = menuService.findMenuById(menuId);
            // Delete the old photo if it exists
            if (existingMenu != null && existingMenu.getPhotoUrl() != null) {
                String oldPhotoUrl = existingMenu.getPhotoUrl();
                String oldPhotoPath = "src/main/resources/static/images/menu/" + menuId + "/" + oldPhotoUrl;
                Path oldPhotoFilePath = Paths.get(oldPhotoPath);

                try {
                    Files.deleteIfExists(oldPhotoFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            menu.setPhotoUrl(newPhoto.getOriginalFilename());

            String uploadDir = "src/main/resources/static/images/menu/" + menu.getId();

            Path uploadPath =  Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){ //memeriksa apakah directory sudah ada
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = newPhoto.getInputStream();) {
                Path filePath = uploadPath.resolve(menu.getPhotoUrl());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException e){
                throw new IOException("Tidak bisa menyimpan gambar yang dikirim: " + menu.getPhotoUrl());
            }
        }
        menu.setId(menuId);
        menuService.updateMenu(menu);
        return "redirect:/menu";
    }

//    delete menu
    @GetMapping("/menu/{menuId}/delete")
    public String deleteMenu(@PathVariable("menuId") int menuId){
        menuService.delete(menuId);

        String deletedDir = "src/main/resources/static/images/menu/" + menuId;

        deleteDirectory(deletedDir);

        return "redirect:/menu";
    }

    private void deleteDirectory(String directoryPath) {
        Path directory = Paths.get(directoryPath);
        try {
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

}
