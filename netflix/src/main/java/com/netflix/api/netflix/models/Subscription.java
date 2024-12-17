package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(nullable = true)
    private int userId;
}

// find a way to restore and backup the database in case of corruption, put everything in a package to send him

