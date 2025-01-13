package com.netflix.api.netflix.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.netflix.api.netflix.controllers")
public class WebConfig implements WebMvcConfigurer {
    // Additional web configuration if needed
}
