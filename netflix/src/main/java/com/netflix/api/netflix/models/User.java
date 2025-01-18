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

    @Column(nullable = false)
    private boolean isActivated;

    @Column(nullable = false)
    private int failedLoginAttempts;
    private LocalDateTime accountLockUntil;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @ManyToOne // Assuming a user can have one subscription, and a subscription can have many users.
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();

//    @ElementCollection
//    @CollectionTable(name = "user_preferences", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "preference")
//    private List<String> preferences = new ArrayList<>();

    private String activationToken; // Token for email verification
    private String resetToken; // Token for password reset
    private String inviteCode;
    private LocalDateTime createdAt;
//    private LocalDateTime tokenExpiryTime; // Token expiration time

    public void addProfile(Profile profile) throws ProfileLimitExceededException
    {
        if (this.profiles.size() >= 4)
        {
            throw new ProfileLimitExceededException("A user can have a maximum of 4 profiles.");
        }

        this.profiles.add(profile);
        profile.setUser(this);
    }

    public void removeProfile(Profile profile)
    {
        this.profiles.remove(profile);
        profile.setUser(null);
    }
}