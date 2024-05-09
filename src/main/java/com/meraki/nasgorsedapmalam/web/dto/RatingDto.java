package com.meraki.nasgorsedapmalam.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RatingDto {
    private int idMenu;
    private float finalRating;
    private int ratingCount;

    public RatingDto(int idMenu, float finalRating, int ratingCount) {
        this.idMenu = idMenu;
        this.finalRating = finalRating;
        this.ratingCount = ratingCount;
    }
}
