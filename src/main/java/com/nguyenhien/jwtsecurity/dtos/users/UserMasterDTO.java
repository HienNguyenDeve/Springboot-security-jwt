package com.nguyenhien.jwtsecurity.dtos.users;

import java.time.Instant;
import java.util.Set;

import com.nguyenhien.jwtsecurity.dtos.MasterDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMasterDTO extends MasterDTO{
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String avatarUrl;

    private String address;

    private Boolean isActive = true;

    private Instant lockedUntil;

    private Set<RoleDTO> roles;
}
