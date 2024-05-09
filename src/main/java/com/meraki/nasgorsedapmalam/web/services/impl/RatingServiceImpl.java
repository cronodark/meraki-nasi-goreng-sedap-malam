package com.meraki.nasgorsedapmalam.web.services.impl;

import com.meraki.nasgorsedapmalam.web.dto.RatingDto;
import com.meraki.nasgorsedapmalam.web.models.Rating;
import com.meraki.nasgorsedapmalam.web.repository.RatingRepository;
import com.meraki.nasgorsedapmalam.web.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public List<Rating> findAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }
}
