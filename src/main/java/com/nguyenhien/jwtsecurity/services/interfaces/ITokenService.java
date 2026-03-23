package com.nguyenhien.jwtsecurity.services.interfaces;

import org.springframework.security.core.Authentication;

public interface ITokenService {
    String generateToken(Authentication authentication);
    Authentication getAuthentication(String token);
}
