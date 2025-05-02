package com.nhlstenden.netflixrefactor.repositories;

import com.nhlstenden.netflixrefactor.dtos.UserDto;
import com.nhlstenden.netflixrefactor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByActivationToken(String token);
    Optional<User> findByResetToken(String token);
}
