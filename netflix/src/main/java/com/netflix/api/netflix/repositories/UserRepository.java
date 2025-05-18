package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<User> findByActivationToken(String activationToken);

    Optional<User> findByResetToken(String token);
}
