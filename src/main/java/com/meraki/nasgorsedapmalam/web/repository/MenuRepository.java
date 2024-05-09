package com.meraki.nasgorsedapmalam.web.repository;


import com.meraki.nasgorsedapmalam.web.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
