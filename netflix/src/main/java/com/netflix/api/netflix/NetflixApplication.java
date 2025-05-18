package com.netflix.api.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@EntityScan("com.netflix.api.netflix.models")
public class NetflixApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(NetflixApplication.class, args);
    }

}
