package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @ManyToOne // Assuming a user can have one subscription, and a subscription can have many users.
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private List<Role> roles = new ArrayList<>();
//@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//@JoinTable(
//        name = "users",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
//)
//private List<Role> roles = new ArrayList<>();
    @ManyToOne // Assuming a user can have one subscription, and a subscription can have many users.
    @JoinColumn(name = "role_id")
    private Role role;

    public User(int userId, String email, String password, boolean activated)
    {
    }
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Profile> profiles = new ArrayList<>();
}
