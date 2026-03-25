package com.nguyenhien.jwtsecurity.dtos.roles;

import com.nguyenhien.jwtsecurity.dtos.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BaseDTO{
    private String name;
    private String description;
}
