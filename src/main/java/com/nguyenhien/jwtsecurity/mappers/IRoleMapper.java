package com.nguyenhien.jwtsecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nguyenhien.jwtsecurity.dtos.roles.RoleCreateUpdateDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleMasterDTO;
import com.nguyenhien.jwtsecurity.entities.Role;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper {
    RoleDTO toDTO(Role entity);

    RoleMasterDTO toMasterDTO(Role entity);

    Role toEntity(RoleCreateUpdateDTO dto);

    // Keep the insertedAt, updateAt, deleteAt fields as they are
    @Mapping(target = "insertedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(RoleCreateUpdateDTO dto, @MappingTarget Role entity);
}
