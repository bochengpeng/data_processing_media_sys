package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subscriptions")
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private SubscriptionTier tier;
    private LocalDate startDate;
    private LocalDate nextBillingDate;
    private boolean isTrialPeriod;

    @Column(name = "subscription_price", nullable = false, columnDefinition = "DOUBLE DEFAULT 7.99")
    private double subscriptionPrice = 7.99;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
}

// find a way to restore and backup the database in case of corruption, put everything in a package to send him

