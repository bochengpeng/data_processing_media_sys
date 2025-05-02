package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean activated = false;
    private String activationToken;
    private LocalDateTime activationTokenExpiry;

    private String resetToken;
    private LocalDateTime resetTokenExpiry;

    private int failedLoginAttempts = 0;
    private LocalDateTime accountLockedUntil;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Profile> profiles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Subscription subscription;

    private String referredByUserId;
    private boolean referralDiscountApplied = false;
}