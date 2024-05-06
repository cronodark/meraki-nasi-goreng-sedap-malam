package com.meraki.nasgorsedapmalam.web.services;

import com.meraki.nasgorsedapmalam.web.dto.RegistrationDto;
import com.meraki.nasgorsedapmalam.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
