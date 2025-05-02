package com.nhlstenden.netflixrefactor.services.serviceimpl;

import com.nhlstenden.netflixrefactor.models.Subscription;
import com.nhlstenden.netflixrefactor.repositories.SubscriptionRepository;
import com.nhlstenden.netflixrefactor.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubscriptionServiceImpl implements SubscriptionService
{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscription.setStartDate(LocalDateTime.now());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found with ID: " + id));
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existing = getSubscriptionById(id);
        existing.setType(subscription.getType());
        existing.setPrice(subscription.getPrice());
        existing.setEndDate(subscription.getEndDate());
        existing.setInTrialPeriod(subscription.isInTrialPeriod());
        return subscriptionRepository.save(existing);
    }

    @Override
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new NoSuchElementException("No subscription found to delete with ID: " + id);
        }
        subscriptionRepository.deleteById(id);
    }
}

