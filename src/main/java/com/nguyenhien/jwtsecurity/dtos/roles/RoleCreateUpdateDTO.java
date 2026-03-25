package com.nguyenhien.jwtsecurity.dtos.roles;

import com.nguyenhien.jwtsecurity.dtos.MasterCreateUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateUpdateDTO extends MasterCreateUpdateDTO{
    private String name;

    private String description;
}
