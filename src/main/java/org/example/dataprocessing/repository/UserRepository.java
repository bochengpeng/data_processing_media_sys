package org.example.dataprocessing.repository;

import org.example.dataprocessing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUserId(Long userId);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
