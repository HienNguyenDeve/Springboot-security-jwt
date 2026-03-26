package com.nguyenhien.jwtsecurity.services.implementations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhien.jwtsecurity.dtos.requests.ChangePasswordRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.LoginRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.LogoutRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.RefreshTokenRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.ResetPasswordRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.requests.ValidateResetPasswordTokenRequestDTO;
import com.nguyenhien.jwtsecurity.dtos.responses.JwtResponse;
import com.nguyenhien.jwtsecurity.dtos.responses.MessageResponse;
import com.nguyenhien.jwtsecurity.dtos.users.UserBaseDTO;
import com.nguyenhien.jwtsecurity.entities.RefreshToken;
import com.nguyenhien.jwtsecurity.entities.User;
import com.nguyenhien.jwtsecurity.mappers.IUserMapper;
import com.nguyenhien.jwtsecurity.repositories.IRoleRepository;
import com.nguyenhien.jwtsecurity.repositories.IUserRepository;
import com.nguyenhien.jwtsecurity.security.UserDetailsImpl;
import com.nguyenhien.jwtsecurity.services.interfaces.IAuthService;
import com.nguyenhien.jwtsecurity.services.interfaces.IRefreshTokenService;
import com.nguyenhien.jwtsecurity.services.interfaces.ITokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService, UserDetailsService {
    private final ITokenService tokenService;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;
    private final ObjectProvider<AuthenticationManager> authenticationManagerProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final IRefreshTokenService refreshTokenService;

    // load user
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user by username and password
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> "ROLE_" + role.getName())
            .map(SimpleGrantedAuthority::new )
            .collect(Collectors.toSet());
        return UserDetailsImpl.build(user);
    }

    // login
    @Override
    @Transactional
    public JwtResponse login(LoginRequestDTO request) {
        try {
            // Check
    
            // UsernamPasswordAuthentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
    
            // AuthenticationManager
            Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
    
            // SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            // Generate JWT
            String accessToken = tokenService.generateToken(authentication);
    
            // Generate Refresh token
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            RefreshToken refreshToken;

            try {
                refreshToken = refreshTokenService.createRefreshToken(user);
            } catch (DataIntegrityViolationException ex) {
                // If we hit a constraint violation, try to find existing token
                log.warn("Constraint violation creating refresh token, checking for existing tokens");
                List<RefreshToken> activeTokens = refreshTokenService.findActiveTokenByUser(user);
                if (activeTokens.isEmpty()) {
                    throw new RuntimeException("Could not create or find valid refresh token");
                }
                refreshToken = activeTokens.get(0);
            }
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            
            UserBaseDTO userInfor = UserBaseDTO
                       .builder()
                       .id(user.getId())
                       .username(user.getUsername())
                       .email(user.getEmail())
                       .roles(roles)
                       .build();
    
            return JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken.getToken())
                        .userInfor(userInfor)
                        .build();
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            throw e;
        }
    }

    // logout
    @Override
    public MessageResponse logout(LogoutRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    // refresh token
    @Override
    public JwtResponse refreshToken(RefreshTokenRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

    // revoke token
    @Override
    public MessageResponse revokeToken(String token, String reason) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'revokeToken'");
    }

    // reset password
    @Override
    public boolean requestResetPassword(ResetPasswordRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestResetPassword'");
    }

    // validate reset password token
    @Override
    public boolean validateResetPasswordToken(ValidateResetPasswordTokenRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateResetPasswordToken'");
    }

    // change password
    @Override
    public boolean changePassword(ChangePasswordRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    // check exist by username
    @Override
    public boolean existsByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByUsername'");
    }

    // check exist by email
    @Override
    public boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByEmail'");
    }

}
