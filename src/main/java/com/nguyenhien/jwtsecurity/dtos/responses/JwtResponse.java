package com.nguyenhien.jwtsecurity.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nguyenhien.jwtsecurity.dtos.users.UserBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private UserBaseDTO userInfor;
}
