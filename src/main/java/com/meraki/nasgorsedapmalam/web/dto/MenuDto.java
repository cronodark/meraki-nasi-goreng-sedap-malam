package com.meraki.nasgorsedapmalam.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MenuDto {
    private int id;
    private String nama;
    private String photoUrl;
    private int harga;
    private String deskripsi;
    private double ratingValue;
    private int ratingCount;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
