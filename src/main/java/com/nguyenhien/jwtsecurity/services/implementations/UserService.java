package com.nguyenhien.jwtsecurity.services.implementations;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhien.jwtsecurity.dtos.requests.RegisterRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.users.UserMasterDTO;
import com.nguyenhien.jwtsecurity.mappers.IUserMapper;
import com.nguyenhien.jwtsecurity.repositories.IRoleRepository;
import com.nguyenhien.jwtsecurity.repositories.IUserRepository;
import com.nguyenhien.jwtsecurity.services.interfaces.IUserSevice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserSevice{
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserMapper userMapper;

    @Override
    @Transactional
    public UserMasterDTO create(RegisterRequestDTO userDTO) {
        // Check if userDTO is null
        if (userDTO == null) {
            throw new IllegalArgumentException("User is null");
        }

        // Check if username is exist
        if (existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("User with username already exists");
        }

        // Check if email is exist
        if (existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("User with email already exists");
        }

        // Check if phone number is exist
        if (existsByPhoneNumber(userDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("User with phone number already exists");
        }

        // Check role and get roles
        var roles = Set.copyOf(roleRepository.findAllById(userDTO.getRoleIds()));
        if (roles.size() != userDTO.getRoleIds().size()) {
            throw new IllegalArgumentException("Role not found");
        }

        // Map and save entity
        var user = userMapper.toEntity(userDTO);
        user.setRoles(roles);
        userRepository.save(user);

        // Send email
        // Return
        return userMapper.toDTO(user);
    }

    private boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
