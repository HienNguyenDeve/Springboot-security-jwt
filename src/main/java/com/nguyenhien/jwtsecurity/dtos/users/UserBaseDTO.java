package com.nguyenhien.jwtsecurity.dtos.users;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDTO{
    private UUID id;
    private String username;
    private String email;
    private List<String> roles;
}
