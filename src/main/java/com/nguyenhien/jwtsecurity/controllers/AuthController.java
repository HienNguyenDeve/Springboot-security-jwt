package com.nguyenhien.jwtsecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhien.jwtsecurity.dtos.requests.LoginRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.LogoutRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.RefreshTokenRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.RegisterRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.responses.JwtResponse;
import com.nguyenhien.jwtsecurity.dtos.responses.MessageResponse;
import com.nguyenhien.jwtsecurity.services.interfaces.IAuthService;
import com.nguyenhien.jwtsecurity.services.interfaces.IUserSevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {
    private final IAuthService authService;
    private final IUserSevice userSevice;

    // register
    @PostMapping("/register")
    @Operation(summary="Sign up an acount", description="Register API",
        responses={
            @ApiResponse(
                responseCode="201",
                description="Register successfully"
            ),
            @ApiResponse(
                responseCode="401",
                description="Register failure"
            )
        }
    )
    public ResponseEntity<?> register(
                @Valid @RequestBody RegisterRequestDTO dto, 
                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var userMasterDTO = userSevice.create(dto);
        return ResponseEntity.ok(userMasterDTO);
    }

    // login
    @PostMapping("/login")
    @Operation(
        summary="Authenticate user",
        description="User authenticate and return JWT",
        responses={
            @ApiResponse(
                responseCode="200",
                description="Successfully authenticated"
            ),
            @ApiResponse(
                responseCode="401",
                description="Invalid username or password"
            )
        }
        
    )
    public ResponseEntity<?> login (
                @Valid @RequestBody LoginRequestDTO loginRequestDTO, 
                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var result = authService.login(loginRequestDTO);
        return ResponseEntity.ok(result);
    }

    // logout
    @PostMapping("/logout")
    @Operation(
        summary = "Logout user",
        description = "Invalidate refresh token for user",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully log out",
                content = @Content(schema = @Schema(implementation = MessageResponse.class))
            )
        }
    )
    public ResponseEntity<MessageResponse> logout (
                @RequestBody LogoutRequestDTO logoutRequestDTO, 
                HttpServletRequest request) {
        var result = authService.logout(logoutRequestDTO);
        return ResponseEntity.ok(result);
    }

    // refresh token
    @PostMapping("/refresh-token")
    @Operation(
        summary="Refresh Token",
        description="Refresh token to get new accesstoken and new refresh token",
        responses={
            @ApiResponse(
                responseCode="200",
                description="Successfully fresh token",
                content=@Content(schema=@Schema(implementation=JwtResponse.class))
            ),
            @ApiResponse(
                responseCode="403",
                description="Invalid refresh token"
            )
        }
    )
    public ResponseEntity<JwtResponse> refreshToken(
        @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO, 
        HttpServletRequest httpServletRequest) {
        var result = authService.refreshToken(refreshTokenRequestDTO);
        return ResponseEntity.ok(result);
    }

    // revoke token

    // forgot password

    // valiate reset password token

    // change password
}
