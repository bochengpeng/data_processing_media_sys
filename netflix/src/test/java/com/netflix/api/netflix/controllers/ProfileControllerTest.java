package com.netflix.api.netflix.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RestController
@RequestMapping("/netflix/user")
public class ProfileControllerTest {

    @GetMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<String> testGetProfile(
            @PathVariable int userId,
            @PathVariable int profileId) {
        return ResponseEntity.ok("Test successful for userId: " + userId + " and profileId: " + profileId);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        map.forEach((key, value) -> System.out.println("Mapped: " + key + " to " + value));
    }
}