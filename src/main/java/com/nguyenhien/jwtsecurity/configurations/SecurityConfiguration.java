package com.nguyenhien.jwtsecurity.configurations;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.nguyenhien.jwtsecurity.services.interfaces.ITokenService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Value("${app.frontend.url}")
    private String frontendUrl;

    private final ITokenService tokenService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // Allow specific origin: clientUrl
        config.setAllowedOrigins(Collections.singletonList(frontendUrl));
        // Allow all headers: Authorization, Content-Type, ...
        config.addAllowedHeader("*");
        // Allow all methods: GET, POST, PUT, DELETE,...
        config.addAllowedMethod("*");

        // Apply the configuration to all paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF
        http.csrf(csrf -> csrf.disable())
                // Add CORS filter
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                // Add Jwt filter
                .addFilterBefore(new JWTFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                // Filter requests
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                                .requestMatchers("/api/v1/roles/**").permitAll()
                                .requestMatchers("/api/v1/users/**").hasRole("Admin")
                                .anyRequest().anonymous())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
