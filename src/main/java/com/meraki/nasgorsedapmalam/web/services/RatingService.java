package com.meraki.nasgorsedapmalam.web.services;


import com.meraki.nasgorsedapmalam.web.models.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> findAllRatings();
    Rating addRating(Rating rating);
}
