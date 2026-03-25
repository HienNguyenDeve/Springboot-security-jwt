package com.nguyenhien.jwtsecurity.services.interfaces;

import com.nguyenhien.jwtsecurity.dtos.requests.RegisterRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.users.UserMasterDTO;

public interface IUserSevice {
    UserMasterDTO create(RegisterRequestDTO userDTO);
}
