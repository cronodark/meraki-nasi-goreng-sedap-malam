package com.meraki.nasgorsedapmalam.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@Builder
public class MenuDto {
    private int id;
    private String nama;
    private String photoUrl;
    private int harga;
    private String deskripsi;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
