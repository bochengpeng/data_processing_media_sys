package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.SubscriptionTier;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionDto {
    private Long subscriptionId;
    private SubscriptionTier tier;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
}

