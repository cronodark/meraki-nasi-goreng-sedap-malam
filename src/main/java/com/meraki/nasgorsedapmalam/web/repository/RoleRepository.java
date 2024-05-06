package com.meraki.nasgorsedapmalam.web.repository;

import com.meraki.nasgorsedapmalam.web.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
