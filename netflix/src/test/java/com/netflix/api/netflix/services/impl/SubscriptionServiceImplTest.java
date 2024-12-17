package com.netflix.api.netflix.services.impl;

import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.models.Subscription;
import com.netflix.api.netflix.models.SubscriptionTier;
import com.netflix.api.netflix.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceImplTest
{

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    private Subscription subscription;

    @BeforeEach
    public void setup()
    {
        // Initialize a Subscription object for testing
        subscription = new Subscription();
        subscription.setSubscriptionId(1);
        subscription.setTier(SubscriptionTier.HD);
        subscription.setStartDate(LocalDate.parse("2024-01-01"));
        subscription.setNextBillingDate(LocalDate.parse("2025-01-01"));
    }

//    @Test
//    public void testCreateSubscription()
//    {
//        // Arrange: Mock the behavior of the repository
//        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
//
//        // Act: Call the method being tested
//        SubscriptionDto subscriptionDto = subscriptionService.createSubscription(subscription);
//
//        // Assert: Verify the result
//        assertNotNull(subscriptionDto);
//        assertEquals(subscription.getSubscriptionId(), subscriptionDto.getSubscriptionId());
//        assertEquals(subscription.getTier(), subscriptionDto.getTier());
//        verify(subscriptionRepository, times(1)).save(subscription);
//    }

//    @Test
//    public void testGetSubscriptionById()
//    {
//        // Arrange: Mock the behavior of the repository
//        when(subscriptionRepository.findById(1)).thenReturn(Optional.of(subscription));
//
//        // Act: Call the method being tested
//        SubscriptionDto subscriptionDto = subscriptionService.getSubscriptionById(1);
//
//        // Assert: Verify the result
//        assertNotNull(subscriptionDto);
//        assertEquals(subscription.getSubscriptionId(), subscriptionDto.getSubscriptionId());
//        assertEquals(subscription.getTier(), subscriptionDto.getTier());
//        verify(subscriptionRepository, times(1)).findById(1);
//    }

//    @Test
//    public void testGetSubscriptionById_NotFound() {
//        // Arrange: Mock the behavior of the repository to return an empty Optional
//        when(subscriptionRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Act & Assert: Verify that an exception is thrown
//        assertThrows(SubscriptionNotFoundException.class, () -> {
//            subscriptionService.getSubscriptionById(1);
//        });
//    }

//    @Test
//    public void testUpdateSubscription() {
//        // Arrange: Mock the behavior of the repository
//        when(subscriptionRepository.findById(1)).thenReturn(Optional.of(subscription));
//        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
//
//        // Act: Call the method being tested
//        SubscriptionDto updatedSubscriptionDto = subscriptionService.updateSubscription(1, subscription);
//
//        // Assert: Verify the result
//        assertNotNull(updatedSubscriptionDto);
//        assertEquals(subscription.getSubscriptionId(), updatedSubscriptionDto.getSubscriptionId());
//        assertEquals(subscription.getTier(), updatedSubscriptionDto.getTier());
//        verify(subscriptionRepository, times(1)).findById(1);
//        verify(subscriptionRepository, times(1)).save(subscription);
//    }

//    @Test
//    public void testDeleteSubscription() {
//        // Arrange: Mock the behavior of the repository
//        when(subscriptionRepository.findById(1)).thenReturn(Optional.of(subscription));
//        doNothing().when(subscriptionRepository).deleteById(1);
//
//        // Act: Call the method being tested
//        subscriptionService.deleteSubscription(1);
//
//        // Assert: Verify that the repository's delete method was called
//        verify(subscriptionRepository, times(1)).deleteById(1);
//    }
}
