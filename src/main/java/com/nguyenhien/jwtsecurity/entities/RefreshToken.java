package com.nguyenhien.jwtsecurity.entities;

import java.beans.Transient;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_tokens")
@Builder
public class RefreshToken extends MasterEntity {
    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @Builder.Default
    private boolean isUsed = false;

    @Builder.Default
    private boolean isRevoked = false;

    private String replacedByToken;

    private String reasonRevoked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName="id")
    private User user;

    @Transient
    public boolean isExpired() {
        return Instant.now().isAfter(expiryDate);
    }

    @Transient
    public boolean isActive() {
        return !isRevoked && !isUsed && !isExpired();
    }

}
