package com.nguyenhien.jwtsecurity.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nguyenhien.jwtsecurity.dtos.roles.RoleCreateUpdateDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleMasterDTO;
import com.nguyenhien.jwtsecurity.entities.Role;
import com.nguyenhien.jwtsecurity.mappers.IRoleMapper;
import com.nguyenhien.jwtsecurity.repositories.IRoleRepository;
import com.nguyenhien.jwtsecurity.services.interfaces.IRoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<RoleMasterDTO> findAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toMasterDTO).toList();
    }

    @Override
    public List<RoleMasterDTO> findByName(String keyword) {
        Specification<Role> specification = buildKeywordSpecification(keyword);
        var roles = roleRepository.findAll(specification);
        return roles.stream().map(roleMapper::toMasterDTO).toList();
    }

    private Specification<Role> buildKeywordSpecification(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }
        return (root, query, cb) -> cb.or(
            cb.like(cb.lower(root.get("name")), 
                "%" + keyword.toLowerCase() + "%"),
            cb.like(cb.lower(root.get("description")), 
                "%" + keyword.toLowerCase() + "%")
        );
    }

    @Override
    public RoleMasterDTO findById(UUID id) {
        var role = roleRepository.findById(id).orElseThrow(() 
            -> new IllegalArgumentException("Role not found"));
        return roleMapper.toMasterDTO(role);
    }

    @Override
    public RoleMasterDTO create(RoleCreateUpdateDTO roleDTO) {
        // Check roleDTO
        if (roleDTO == null) {
            throw new IllegalArgumentException("Role is null");
        }

        // Check existingRole
        var existingRole = roleRepository.findByName(roleDTO.getName());
        if (existingRole != null) {
            throw new IllegalArgumentException("Role already exists");
        }

        // Save
        var role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toMasterDTO(role);
    }

    @Override
    public RoleMasterDTO update(UUID id, RoleCreateUpdateDTO roleDTO) {
        // Check roleDTO
        if (roleDTO == null) {
            throw new IllegalArgumentException("Role is null");
        }

        // Check existing name of role
        var existingRole = roleRepository.findByName(roleDTO.getName());
        if (existingRole != null && !existingRole.getId().equals(id)) {
            throw new IllegalArgumentException("Role already exists");
        }

        // Check existing Role
        var role = roleRepository.findById(id).orElseThrow(() 
            -> new IllegalArgumentException("Role not found"));

        // Update
        roleMapper.updateEntity(roleDTO, role);
        role.setId(id);
        roleRepository.save(role);
        return roleMapper.toMasterDTO(role);
    }

    @Override
    public boolean delete(UUID id) {
        var role = roleRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("Role not found"));
        roleRepository.delete(role);
        return !roleRepository.existsById(id);
    }
}
