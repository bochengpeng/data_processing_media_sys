package com.netflix.api.netflix.models;

import com.netflix.api.netflix.exception.ProfileLimitExceededException;
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
    private boolean isActivated;

    @Column
    private int failedLoginAttempts;
    private LocalDateTime accountLockUntil;

    // Assuming a user can have one subscription, and a subscription can have many users.
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();

    private String activationToken; // Token for email verification
    private String resetToken; // Token for password reset
    private String inviteCode;
    private LocalDateTime createdAt;
}