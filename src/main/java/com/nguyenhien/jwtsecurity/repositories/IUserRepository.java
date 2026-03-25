package com.nguyenhien.jwtsecurity.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nguyenhien.jwtsecurity.entities.User;

public interface IUserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);
    User findByEmailOrPhoneNumber(String email, String phoneNumber);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    // Admin search functions
    Page<User> findByIsActive(Boolean isActive, Pageable pageable);
    Page<User> findByUsernameContainingOrEmailContainingOrFirstNameContainingOrLastNameContaining(
        String username, String email, String firstName, String lastName, Pageable pageable);

}
