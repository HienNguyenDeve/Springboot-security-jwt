package com.nguyenhien.jwtsecurity.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JWTConfiguration {
    private String secret;
    private long expirationMs;
    private long refreshExpirationMs;
}
