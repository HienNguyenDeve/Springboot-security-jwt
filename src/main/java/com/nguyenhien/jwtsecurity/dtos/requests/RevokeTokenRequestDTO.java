package com.nguyenhien.jwtsecurity.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevokeTokenRequestDTO {
    @NotBlank(message = "Token is required")
    private String token;
    
    private String reason;
}
