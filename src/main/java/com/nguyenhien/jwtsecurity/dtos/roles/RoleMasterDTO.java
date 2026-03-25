package com.nguyenhien.jwtsecurity.dtos.roles;

import com.nguyenhien.jwtsecurity.dtos.MasterDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMasterDTO extends MasterDTO{
    private String name;

    private String description;
}
