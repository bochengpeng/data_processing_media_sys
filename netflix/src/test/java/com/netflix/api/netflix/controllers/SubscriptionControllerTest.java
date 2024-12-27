package com.netflix.api.netflix.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.netflix.api.netflix.dto.SubscriptionDto;
import com.netflix.api.netflix.models.SubscriptionTier;
import com.netflix.api.netflix.services.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false) // Disable Spring Security filters
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;

    private SubscriptionDto subscriptionDto;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        subscriptionDto = SubscriptionDto.builder()
                .subscriptionId(1)
                .tier(SubscriptionTier.HD)
                .isTrialPeriod(true)
                .startDate(LocalDate.of(2024, 12, 1))
                .nextBillingDate(LocalDate.of(2025, 1, 1))
                .build();
    }

    @Test
    void testCreateSubscription() throws Exception
    {
        int userId = 101;

        when(subscriptionService.createSubscription(eq(userId), any(SubscriptionDto.class)))
                .thenReturn(subscriptionDto);

        mockMvc.perform(post("/netflix/user/{userId}/subscription", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriptionDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subscriptionId").value(subscriptionDto.getSubscriptionId()))
                .andExpect(jsonPath("$.tier").value(subscriptionDto.getTier().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(subscriptionDto.isTrialPeriod()));
    }

    @Test
    void testGetSubscriptionByUserId() throws Exception
    {
        int userId = 101;

        when(subscriptionService.getSubscriptionByUserId(userId)).thenReturn(subscriptionDto);

        mockMvc.perform(get("/netflix/user/{userId}/subscription", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subscriptionId").value(subscriptionDto.getSubscriptionId()))
                .andExpect(jsonPath("$.tier").value(subscriptionDto.getTier().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(subscriptionDto.isTrialPeriod()));
    }

    @Test
    void testGetSubscriptionById() throws Exception
    {
        int userId = 101;
        int subscriptionId = 1;

        when(subscriptionService.getSubscriptionById(userId, subscriptionId)).thenReturn(subscriptionDto);

        mockMvc.perform(get("/netflix/user/{userId}/subscription/{id}", userId, subscriptionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subscriptionId").value(subscriptionDto.getSubscriptionId()))
                .andExpect(jsonPath("$.tier").value(subscriptionDto.getTier().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(subscriptionDto.isTrialPeriod()));
    }

    @Test
    void testUpdateSubscription() throws Exception
    {
        int userId = 101;
        int subscriptionId = 1;
        SubscriptionDto updatedSubscriptionDto = SubscriptionDto.builder()
                .subscriptionId(subscriptionId)
                .tier(SubscriptionTier.MONTHLY_PRICE)
                .isTrialPeriod(false)
                .startDate(LocalDate.of(2024, 12, 10))
                .nextBillingDate(LocalDate.of(2025, 1, 10))
                .build();

        when(subscriptionService.updateSubscription(eq(userId), eq(subscriptionId), any(SubscriptionDto.class)))
                .thenReturn(updatedSubscriptionDto);

        mockMvc.perform(put("/netflix/user/{userId}/subscription/{id}", userId, subscriptionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSubscriptionDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subscriptionId").value(updatedSubscriptionDto.getSubscriptionId()))
                .andExpect(jsonPath("$.tier").value(updatedSubscriptionDto.getTier().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(updatedSubscriptionDto.isTrialPeriod()));
    }

    @Test
    void testDeleteSubscription() throws Exception
    {
        int userId = 101;
        int subscriptionId = 1;

        doNothing().when(subscriptionService).deleteSubscription(userId, subscriptionId);

        mockMvc.perform(delete("/netflix/user/{userId}/subscription/{id}", userId, subscriptionId))
                .andExpect(status().isOk())
                .andExpect(content().string("Subscription deleted successfully"));
    }
}
