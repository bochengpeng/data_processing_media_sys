//package com.netflix.api.netflix.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netflix.api.netflix.dto.SubscriptionDto;
//import com.netflix.api.netflix.services.SubscriptionService;
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static java.lang.reflect.Array.get;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
//
//@WebMvcTest(controllers = SubscriptionController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class SubscriptionControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SubscriptionService subscriptionService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private SubscriptionDto subscriptionDto;
//
//    @BeforeEach
//    public void init() {
//        subscriptionDto = SubscriptionDto.builder()
//                .id(1)
//                .userId(1)
//                .plan("Premium")
//                .startDate("2024-01-01")
//                .endDate("2025-01-01")
//                .build();
//    }
//
//    @Test
//    public void SubscriptionController_CreateSubscription_ReturnCreated() throws Exception {
//        // Mocking service layer behavior
//        given(subscriptionService.createSubscription(ArgumentMatchers.anyInt(), ArgumentMatchers.any()))
//                .willAnswer((invocation) -> invocation.getArgument(1));
//
//        // Perform the POST request to create a subscription
//        ResultActions response = mockMvc.perform(post("/api/subscriptions/users/{userId}", 1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(subscriptionDto)));
//
//        // Assertions to verify the expected outcome
//        response.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.plan", CoreMatchers.is(subscriptionDto.getTier())));
//    }
//
//    @Test
//    public void SubscriptionController_GetSubscriptionById_ReturnSubscriptionDto() throws Exception {
//        // Mocking service layer behavior
//        int subscriptionId = 1;
//        when(subscriptionService.getSubscriptionById(subscriptionId)).thenReturn(subscriptionDto);
//
//        // Perform the GET request to fetch the subscription by ID
//        ResultActions response = mockMvc.perform(get("{subscriptionId}", subscriptionId)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        // Assertions to verify the expected outcome
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.plan", CoreMatchers.is(subscriptionDto.getTier())));
//    }
//
////    @Test
////    public void SubscriptionController_GetSubscriptionsByUserId_ReturnSubscriptionDtos() throws Exception {
////        // Mocking service layer behavior
////        when(subscriptionService.getSubscriptionsByUserId(1)).thenReturn(java.util.Arrays.asList(subscriptionDto));
////
////        // Perform the GET request to fetch subscriptions by user ID
////        ResultActions response = mockMvc.perform(get("/api/subscriptions/users/{userId}", 1)
////                .contentType(MediaType.APPLICATION_JSON));
////
////        // Assertions to verify the expected outcome
////        response.andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
////    }
//
//    @Test
//    public void SubscriptionController_UpdateSubscription_ReturnUpdatedSubscriptionDto() throws Exception {
//        // Mocking service layer behavior
//        int subscriptionId = 1;
//        when(subscriptionService.updateSubscription(ArgumentMatchers.anyInt(), ArgumentMatchers.any()))
//                .thenReturn(subscriptionDto);
//
//        // Perform the PUT request to update the subscription
//        ResultActions response = mockMvc.perform(put("/api/subscriptions/{subscriptionId}", subscriptionId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(subscriptionDto)));
//
//        // Assertions to verify the expected outcome
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.plan", CoreMatchers.is(subscriptionDto.getTier())));
//    }
//
//    @Test
//    public void SubscriptionController_DeleteSubscription_ReturnOk() throws Exception {
//        // Mocking service layer behavior
//        int subscriptionId = 1;
//        doNothing().when(subscriptionService).deleteSubscription(subscriptionId);
//
//        // Perform the DELETE request to delete the subscription
//        ResultActions response = mockMvc.perform(delete("/api/subscriptions/{subscriptionId}", subscriptionId)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        // Assertions to verify the expected outcome
//        response.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}
