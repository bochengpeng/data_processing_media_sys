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
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private boolean isActivated;
    private int failedLoginAttempts;
    private LocalDateTime accountLockUntil;
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @ManyToOne // Assuming a user can have one subscription, and a subscription can have many users.
    @JoinColumn(name = "role_id")
    private Role role;

    public User(int userId, String email, String password, boolean activated)
    {
    }
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Profile> profiles = new ArrayList<>();
}
