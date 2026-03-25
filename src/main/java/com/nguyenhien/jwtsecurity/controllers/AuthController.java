package com.nguyenhien.jwtsecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhien.jwtsecurity.dtos.requests.RegisterRequestDTO;
import com.nguyenhien.jwtsecurity.services.interfaces.IAuthService;
import com.nguyenhien.jwtsecurity.services.interfaces.IUserSevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var userMasterDTO = userSevice.create(dto);
        return ResponseEntity.ok(userMasterDTO);
    }

    // login

    // logout

    // refresh token

    // revoke token

    // forgot password

    // valiate reset password token

    // change password
}
