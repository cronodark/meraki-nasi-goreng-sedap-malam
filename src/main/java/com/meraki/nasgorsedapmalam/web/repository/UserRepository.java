package com.meraki.nasgorsedapmalam.web.repository;

import com.meraki.nasgorsedapmalam.web.dto.RegistrationDto;
import com.meraki.nasgorsedapmalam.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    UserEntity findFirstByUsername(String username);
}
