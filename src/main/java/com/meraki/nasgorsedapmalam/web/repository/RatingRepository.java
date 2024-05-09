package com.meraki.nasgorsedapmalam.web.repository;

import com.meraki.nasgorsedapmalam.web.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByIdMenu(int menuId);
}
