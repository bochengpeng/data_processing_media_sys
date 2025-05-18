package com.netflix.api.netflix.dto;

import com.netflix.api.netflix.models.SubscriptionTier;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

//    @Min(value = 1, message = "Subscription ID must be greater than 0")
    private int subscriptionId;

    @NotNull(message = "Subscription tier is required")
    private SubscriptionTier tier;

//    @NotNull(message = "Start date is required")
//    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "Next billing date is required")
    @Future(message = "Next billing date must be in the future")
    private LocalDate nextBillingDate;

    private boolean isTrialPeriod;
}