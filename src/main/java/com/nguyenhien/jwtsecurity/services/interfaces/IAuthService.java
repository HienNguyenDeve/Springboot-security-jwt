package com.nguyenhien.jwtsecurity.services.interfaces;

import com.nguyenhien.jwtsecurity.dtos.requests.ChangePasswordRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.LoginRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.LogoutRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.RefreshTokenRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.ResetPasswordRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.ValidateResetPasswordTokenRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.responses.JwtResponse;
import com.nguyenhien.jwtsecurity.dtos.responses.MessageResponse;

public interface IAuthService {

    // login
    JwtResponse login (LoginRequestDTO request);

    // logout
    MessageResponse logout (LogoutRequestDTO request);

    // refresh token
    JwtResponse refreshToken (RefreshTokenRequestDTO request);

    // revoke token
    MessageResponse revokeToken (String token, String reason);


    // forgot password
    boolean requestResetPassword (ResetPasswordRequestDTO request);

    // validate reset password token
    boolean validateResetPasswordToken (ValidateResetPasswordTokenRequestDTO request);

    // reset password
    boolean changePassword (ChangePasswordRequestDTO request);

    boolean existsByUsername (String username);
    boolean existsByEmail (String email);
}
