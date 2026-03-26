package com.nguyenhien.jwtsecurity.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.nguyenhien.jwtsecurity.entities.RefreshToken;
import com.nguyenhien.jwtsecurity.entities.User;

public interface IRefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyExpiration(RefreshToken token);
    RefreshToken useToken(RefreshToken token, String replacedByToken);
    void revokeToken(RefreshToken token, String reason);
    void deleteByUser(User user);
    List<RefreshToken> findActiveTokenByUser(User user);
    void purgeExpiredTokens();
}
