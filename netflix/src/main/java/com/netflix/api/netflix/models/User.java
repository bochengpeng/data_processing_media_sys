package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String username;

    @Column
    private boolean isActivated = false;

    @Column
    private int failedLoginAttempts = 0;
    private LocalDateTime accountLockUntil;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Profile> profiles = new ArrayList<>();

    private String activationToken; // Token for email verification
    private LocalDateTime activationTokenExpiry;
    private String resetToken; // Token for password reset
    private LocalDateTime resetTokenExpiry;
    private String inviteCode;
    private boolean referralDiscountApplied = false;
    private LocalDateTime createdAt;
}