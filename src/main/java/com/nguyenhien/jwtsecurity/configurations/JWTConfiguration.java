package com.nguyenhien.jwtsecurity.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class JWTConfiguration {
    private String secretKey;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;
}
