package com.meraki.nasgorsedapmalam.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private int id;
    private String nama;
    private String email;
    private char jk;
    private String role;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
