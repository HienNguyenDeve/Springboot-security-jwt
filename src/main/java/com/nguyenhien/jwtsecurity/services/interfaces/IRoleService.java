package com.nguyenhien.jwtsecurity.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.nguyenhien.jwtsecurity.dtos.roles.RoleCreateUpdateDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleMasterDTO;

public interface IRoleService {
    List<RoleMasterDTO> findAll();

    List<RoleMasterDTO> findByName(String keyword);

    RoleMasterDTO findById(UUID id);

    RoleMasterDTO create(RoleCreateUpdateDTO roleDTO);

    RoleMasterDTO update(UUID id, RoleCreateUpdateDTO roleDTO);

    boolean delete(UUID id);
}
