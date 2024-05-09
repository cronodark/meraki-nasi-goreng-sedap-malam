package com.meraki.nasgorsedapmalam.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    @NotEmpty(message = "Username harus diisi!")
    private String username;
    @NotEmpty(message = "Password harus diisi!")
    private String password;
    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
