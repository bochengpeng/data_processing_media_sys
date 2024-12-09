package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String password;
    private Boolean isActivated;
    private int failedLoginAttempts;
    private LocalDateTime accountLockUntil;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Profile> profiles;


    @ManyToOne // Assuming a user can have one subscription, and a subscription can have many users.
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    public User() {

    }
    public User(Long userId, String email, String password, Subscription subscription, List<Profile> profiles)
    {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isActivated = true;
        this.failedLoginAttempts = 0;
        this.accountLockUntil = LocalDateTime.now();
        this.profiles = profiles;
        this.subscription = subscription;
    }


    public Long getUserId()
    {
        return this.userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Boolean getIsActivated()
    {
        return this.isActivated;
    }

    public void setActivated(Boolean activated)
    {
        isActivated = activated;
    }

    public int getFailedLoginAttempts()
    {
        return this.failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts)
    {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public LocalDateTime getAccountLockUntil()
    {
        return this.accountLockUntil;
    }

    public void setAccountLockUntil(LocalDateTime accountLockUntil)
    {
        this.accountLockUntil = accountLockUntil;
    }

    public List<Profile> getProfiles()
    {
        return this.profiles;
    }

    public void setProfiles(List<Profile> profiles)
    {
        this.profiles = profiles;
    }

    public Subscription getSubscription()
    {
        return this.subscription;
    }

    public void setSubscription(Subscription subscription)
    {
        this.subscription = subscription;
    }


}
