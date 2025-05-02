package com.nhlstenden.netflixrefactor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SubscriptionTier type;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean inTrialPeriod = true;
    private double price;

    @OneToOne(mappedBy = "subscription", fetch = FetchType.LAZY)
    private User user;
}
