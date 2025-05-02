package com.nhlstenden.netflixrefactor.models;

import lombok.Getter;

@Getter
public enum SubscriptionTier {
    SD(7.99),
    HD(10.99),
    UHD(13.99);

    private final double price;

    SubscriptionTier(double price) {
        this.price = price;
    }

}
