package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByName(String name);
}
