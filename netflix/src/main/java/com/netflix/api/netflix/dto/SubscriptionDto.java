package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.SubscriptionTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SubscriptionDto
{
    private int subscriptionId;
    private SubscriptionTier tier;
    private LocalDate startDate;
    private LocalDate nextBillingDate;
    private boolean isTrialPeriod;
}

