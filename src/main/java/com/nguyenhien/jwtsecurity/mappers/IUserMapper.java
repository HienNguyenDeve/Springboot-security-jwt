package com.nguyenhien.jwtsecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.nguyenhien.jwtsecurity.dtos.requests.RegisterRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.users.UserMasterDTO;
import com.nguyenhien.jwtsecurity.entities.User;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface IUserMapper {
    User toEntity(RegisterRequestDTO dto);

    UserMasterDTO toDTO(User entity);
}
