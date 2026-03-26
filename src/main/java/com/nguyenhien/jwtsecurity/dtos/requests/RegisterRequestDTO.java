package com.nguyenhien.jwtsecurity.dtos.requests;

import java.util.Set;
import java.util.UUID;

import com.nguyenhien.jwtsecurity.commons.validations.ValidPassword;
import com.nguyenhien.jwtsecurity.commons.validations.ValidVnPhone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;

    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @ValidVnPhone
    private String phoneNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    private String address;

    private Set<UUID> roleIds;
}
