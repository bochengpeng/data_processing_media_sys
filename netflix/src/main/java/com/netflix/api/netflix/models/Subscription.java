package com.netflix.api.netflix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    private SubscriptionTier tier;
    private LocalDate startDate;
    private LocalDate nextBillingDate;
    private boolean isTrialPeriod;

    @Column(name = "subscription_price", nullable = false)
    private double subscriptionPrice = 7.99;

    @OneToOne(mappedBy = "subscription", fetch = FetchType.LAZY)
    private User user;
}

