package com.nguyenhien.jwtsecurity.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nguyenhien.jwtsecurity.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, UUID>, JpaSpecificationExecutor<Role>{
    Role findByName(String name);
}
