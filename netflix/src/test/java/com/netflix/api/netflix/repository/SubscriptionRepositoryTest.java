package com.netflix.api.netflix.repository;

import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.SubscriptionTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Automatically configures an in-memory database and repository
public class SubscriptionRepositoryTest
{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private Subscription subscription;

    @BeforeEach
    public void setup()
    {
        // Create a new subscription for testing
        subscription = new Subscription();
        subscription.setTier(SubscriptionTier.HD);
        subscription.setStartDate(LocalDate.parse("2024-01-01"));
        subscription.setNextBillingDate(LocalDate.parse("2025-01-01"));
    }

    @Test
    public void testCreateSubscription()
    {
        // Save the subscription
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Assert that the subscription is saved correctly
        assertThat(savedSubscription).isNotNull();
        assertThat(savedSubscription.getSubscriptionId()).isGreaterThan(0);
    }

    @Test
    public void testFindById()
    {
        // Save the subscription
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Find the saved subscription by its ID
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(savedSubscription.getSubscriptionId());

        // Assert that the subscription is found
        assertThat(foundSubscription).isPresent();
        assertThat(foundSubscription.get().getTier()).isEqualTo(savedSubscription.getTier());
    }

    @Test
    public void testPagination()
    {
        // Save a few subscriptions for pagination
        for (int i = 0; i < 5; i++)
        {
            Subscription sub = new Subscription();
            sub.setTier(SubscriptionTier.MONTHLY_PRICE);
            sub.setStartDate(LocalDate.parse("2024-01-01"));
            sub.setNextBillingDate(LocalDate.parse("2025-01-01"));
            subscriptionRepository.save(sub);
        }

        // Test pagination
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        assertThat(subscriptions).hasSize(5);  // Ensure there are 5 subscriptions

        // You can use Pageable for pagination if needed. Example:
        // Page<Subscription> page = subscriptionRepository.findAll(PageRequest.of(0, 3)); // Page 0, size 3
        // assertThat(page.getContent()).hasSize(3);  // Ensure the first page has 3 subscriptions
    }

    @Test
    public void testDeleteSubscription()
    {
        // Save the subscription
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Delete the saved subscription
        subscriptionRepository.deleteById(savedSubscription.getSubscriptionId());

        // Assert that the subscription no longer exists
        Optional<Subscription> deletedSubscription = subscriptionRepository.findById(savedSubscription.getSubscriptionId());
        assertThat(deletedSubscription).isNotPresent();
    }
}
