package org.example.dataprocessing.entity;
import jakarta.persistence.*;

@Entity
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private SubscriptionTier tier;

    // Constructors
    public Subscription()
    {
    }

    public Subscription(SubscriptionTier tier) {
        this.tier = tier;
    }

    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public SubscriptionTier getTier()
    {
        return tier;
    }

    public void setTier(SubscriptionTier tier)
    {
        this.tier = tier;
    }

    @Override
    public String toString()
    {
        return "Subscription{" +
                "id=" + id +
                ", tier=" + tier +
                '}';
    }
}
