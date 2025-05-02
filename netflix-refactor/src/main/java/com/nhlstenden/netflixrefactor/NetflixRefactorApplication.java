package com.nhlstenden.netflixrefactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@EntityScan("com.nhlstenden.netflixrefactor.models")
public class NetflixRefactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixRefactorApplication.class, args);
	}

}
