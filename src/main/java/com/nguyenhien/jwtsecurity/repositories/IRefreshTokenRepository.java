package com.nguyenhien.jwtsecurity.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nguyenhien.jwtsecurity.entities.RefreshToken;
import com.nguyenhien.jwtsecurity.entities.User;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, UUID>{
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUser(User user);
    List<RefreshToken> findByUserAndIsRevokedFalseAndIsUsedFalse(User user);

    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.expiryDate < :now")
    int deleteAllExpiredTokens(@Param("now") Instant now);
    
    @Modifying
    @Query("UPDATE RefreshToken r SET r.isRevoked = true, r.reasonRevoked = 'User logout' WHERE r.user = :user")
    int revokeAllUserTokens(@Param("user") User user);
}
